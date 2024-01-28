package com.example.s21974_bank;

import com.example.s21974_bank.service.AccountService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class S21974BankApplication {

    public static void main(String[] args) {

        //SpringApplication.run(S21974BankApplication.class, args);

        ConfigurableApplicationContext context = SpringApplication.run(S21974BankApplication.class);

        AccountService accountService = context.getBean("accountService", AccountService.class);


    }


}
