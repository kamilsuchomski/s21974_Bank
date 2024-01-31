package com.example.s21974_bank.controler;

import com.example.s21974_bank.model.account.Account;
import com.example.s21974_bank.repository.AccountRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.Objects;

import static com.example.s21974_bank.model.account.AccountCurrency.EUR;
import static com.example.s21974_bank.model.account.AccountCurrency.PLN;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
class AccountControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void clearData(){
        accountRepository.deleteAccounts();
    }



    @Test
    void shouldGetAccountByGreaterSaldoParam(){
        Account account1 = new Account(null, "John", "Smith", 15.54, PLN, "11223344556");
        Account account2 = new Account(null, "Paul", "Kovalsky", 135.51, PLN, "78893344556");
        Account account3 = new Account(null, "Jack", "Otterbyed", 121.35, PLN, "78893453256");

        accountRepository.create(account1);
        accountRepository.create(account2);
        accountRepository.create(account3);

        Account[] result = webTestClient.get()
                .uri("/account?minimalSaldo=120")
                .exchange()
                .expectStatus().isOk()
                .expectBody(Account[].class)
                .returnResult()
                .getResponseBody();

        if (result != null) {
            assertEquals(2, result.length);
        }
    }

    @Test
    void shouldGetAccountByPathVariable(){
        Account account = new Account(null, "John", "Smith", 15.54, EUR, "11223344556");
        Account createdAccount = accountRepository.create(account);

        Account result = webTestClient.get()
                .uri("/account/" + createdAccount.getId())
                .exchange()
                .expectStatus().isOk()
                .expectBody(Account.class)
                .returnResult()
                .getResponseBody();

        assertEquals(createdAccount.getId(), Objects.requireNonNull(result).getId());
        assertEquals(createdAccount.getImie(), Objects.requireNonNull(result).getImie());
        assertEquals(createdAccount.getNazwisko(), Objects.requireNonNull(result).getNazwisko());
        assertEquals(createdAccount.getSaldo(), Objects.requireNonNull(result).getSaldo());
        assertEquals(createdAccount.getPesel(), Objects.requireNonNull(result).getPesel());
    }
    @Test
    void shouldCreateAccount() {
        Account account = new Account(null, "John", "Smith", 15.54, EUR, "11223344556");
        Account result = webTestClient.post()
                .uri("account/create")
                .bodyValue(account)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(Account.class)
                .returnResult()
                .getResponseBody();


            assertEquals(account.getPesel(), Objects.requireNonNull(result).getPesel());
            assertNotNull(result.getId());

    }
}