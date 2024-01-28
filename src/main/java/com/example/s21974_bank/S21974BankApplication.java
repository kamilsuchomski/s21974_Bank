package com.example.s21974_bank;

import com.model.account.Account;
import com.model.account.AccountCurrency;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class S21974BankApplication {

    public static void main(String[] args) {
        SpringApplication.run(S21974BankApplication.class, args);
    }

    Account account1 = new Account(1, "Jan", "Kowalski", 456.12, AccountCurrency.PLN , "123123123");



}
