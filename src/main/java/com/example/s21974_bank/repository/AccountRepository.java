package com.example.s21974_bank.repository;

import com.example.s21974_bank.model.account.Account;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class AccountRepository {
    List<Account> accountList = new ArrayList<>();
    
    public Account create(Account account){
        account.setId(accountList.size());
        accountList.add(account);
        
        return account;
    }

    public Optional<Account> getByAccountId(Integer id){
        return accountList.stream()
                .filter(account -> account.getId().equals(id))
                .findFirst();
    }

    public List<Account> getGreaterAccounts(Double saldo){
        return accountList.stream()
                .filter(account -> account.getSaldo() > saldo)
                .collect(Collectors.toList());
    }
}
