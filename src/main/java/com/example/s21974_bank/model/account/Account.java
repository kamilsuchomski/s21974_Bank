package com.example.s21974_bank.model.account;

import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
public class Account {
    private Integer id;
    private String imie;
    private String nazwisko;
    private Double saldo;
    private AccountCurrency waluta;
    private String pesel;
}
