package com.example.s21974_bank.controler;

import com.example.s21974_bank.model.account.Account;
import com.example.s21974_bank.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.lang.Double;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {


    private final AccountService accountService;

    @GetMapping
    public ResponseEntity<List<Account>> getAccountByGreaterSaldoParam(@RequestParam(name = "minimalSaldo") Double minimalSaldo){
        List<Account> accountList = accountService.getGreaterAccounts(minimalSaldo);

        return  ResponseEntity.ok(accountList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccountByPathVariable(@PathVariable Integer id){
        Account account = accountService.getByAccountId(id);

        return ResponseEntity.ok(account);
    }

    @PostMapping("/create")
    public ResponseEntity<Account> createAccount(@RequestBody Account account){
        Account createdAccount = accountService.createAccount(account);

        return ResponseEntity
                .status(HttpStatusCode.valueOf(201))
                .body(createdAccount);
    }

}
