package com.example.s21974_bank.service;

import com.example.s21974_bank.exception.AccountNotFoundException;
import com.example.s21974_bank.exception.ValidationException;
import com.example.s21974_bank.model.account.Account;
import com.example.s21974_bank.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;

    public Account createAccount(Account account){
        Map<String, String> validationErrors = new HashMap<>();
        if(account.getSaldo() < 0 || account.getSaldo().isNaN()){
            validationErrors.put("saldo", "Saldo musi byc dodatnia liczba");
        }
        if(!account.getPesel().matches("[0-9]+") || account.getPesel().length() != 11){
            validationErrors.put("pesel", "Pesel musi byc jedenastocyfrowa liczba");
        }
        if(account.getWaluta() == null){
            validationErrors.put("waluta", "Waluta musi byc poprawna (EUR, PLN, USD)");
        }

        if(account.getImie() == null){
            validationErrors.put("imie", "Musisz podac imie");
        }

        if(account.getImie() == null){
            validationErrors.put("nazwisko", "Musisz podac nazwisko");
        }

        if(!validationErrors.isEmpty()){
            throw new ValidationException(validationErrors);
        }

        accountRepository.create(account);

        return account;
    }

    public Account getByAccountId(Integer id){
        Optional<Account> account = accountRepository.getByAccountId(id);

        return account.orElseThrow(() -> new AccountNotFoundException("Konto o numerze " + id + "nie istnieje"));
    }

    public List<Account> getGreaterAccounts(Double saldo){

        return accountRepository.getGreaterAccounts(saldo);
    }


}
