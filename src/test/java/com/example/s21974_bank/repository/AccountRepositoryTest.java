package com.example.s21974_bank.repository;

import com.example.s21974_bank.model.account.Account;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.example.s21974_bank.model.account.AccountCurrency.PLN;
import static com.example.s21974_bank.model.account.AccountCurrency.USD;
import static org.junit.jupiter.api.Assertions.*;

class AccountRepositoryTest {

    private static AccountRepository accountRepository;
    @BeforeAll
    public static void prepareData(){
        accountRepository = new AccountRepository();
    }

    @AfterEach
    public void clearData(){
        accountRepository.deleteAccounts();
    }

    @Test
    void shouldCreateAccount(){
        Account account = new Account(null, "Johnny", "Somehow", 9012.76, USD, "33123101122");
        Account result = assertDoesNotThrow(() -> accountRepository.create(account));

        assertNotNull(result.getId());
        assertEquals(account.getImie(), result.getImie());
    }

    @Test
    void shouldGetByAccountId(){
        Account account = new Account(432, "Johnny", "Somehow", 9012.76, USD, "33123101122");
        Account createdAccount = accountRepository.create(account);

        Account result = assertDoesNotThrow(() -> accountRepository.getByAccountId(createdAccount.getId()).get());

        assertEquals(account.getId(), result.getId());
    }

    @Test
    void shouldGetGreaterAccounts(){
        Account account1 = new Account(null, "Johnny", "Somehow", 90.12, PLN, "33123101122");
        Account account2 = new Account(null, "John", "Smith", 15.54, PLN, "11223344556");
        Account account3 = new Account(null, "Paul", "Kovalsky", 135.51, PLN, "78893344556");
        Account account4 = new Account(null, "Jack", "Otterbyed", 121.35, PLN, "78893453256");

        accountRepository.create(account1);
        accountRepository.create(account2);
        accountRepository.create(account3);
        accountRepository.create(account4);

        assertEquals(3, accountRepository.getGreaterAccounts(90.1).size());
    }

}