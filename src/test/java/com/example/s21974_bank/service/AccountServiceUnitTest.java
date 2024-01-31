package com.example.s21974_bank.service;

import com.example.s21974_bank.exception.ValidationException;
import com.example.s21974_bank.model.account.Account;
import com.example.s21974_bank.repository.AccountRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.example.s21974_bank.model.account.AccountCurrency.USD;
import static org.junit.jupiter.api.Assertions.*;

class AccountServiceUnitTest {

    private static AccountRepository accountRepository;
    private static AccountService accountService;

    @BeforeAll
    public static void prepareData(){
        accountRepository = new AccountRepository();
        accountService = new AccountService(accountRepository);
    }

    @AfterEach
    public void clearData(){
        accountRepository.deleteAccounts();
    }

    @Test
    void shouldThrowExceptionWhenAccountPeselIsNAN() {
        AccountService accountService = new AccountService(accountRepository);
        Account account = new Account(null, "Johnny", "Walkier", 199.99, USD, "ABCDEFGHIJK");

        assertThrows(ValidationException.class, () -> accountService.createAccount(account));
    }

    @Test
    void shouldThrowExceptionWhenAccountSaldoIsMinus() {
        AccountService accountService = new AccountService(accountRepository);
        Account account = new Account(null, "Johnny", "Walkier", -199.99, USD, "12345678901");

        assertThrows(ValidationException.class, () -> accountService.createAccount(account));
    }

    @Test
    void shouldThrowExceptionWhenAccountImieIsNull() {
        AccountService accountService = new AccountService(accountRepository);
        Account account = new Account(null, "", "Walkier", 199.99, USD, "ABCDEFGHIJK");

        assertThrows(ValidationException.class, () -> accountService.createAccount(account));
    }
}