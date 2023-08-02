package com.codurance.atm.tld;

import com.codurance.atm.account.Account;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AccountShould {
    @Test
    void should_persiste_a_balance() {
        //Arrange
        String accountNumber = "RANDOM_ACCOUNT_NUMBER";
        Integer balance = 10;
        String expectedBalance = "10";
        //Act
        Account account = new Account(accountNumber, balance);
        //Assert
        assertEquals(expectedBalance, account.balance());
    }

    @Test
    void should_persiste_an_account_number() {
        //Arrange
        String accountNumber = "RANDOM_ACCOUNT_NUMBER";
        Integer balance = 10;
        //Act
        Account account = new Account(accountNumber, balance);
        //Assert
        assertEquals(accountNumber, account.accountNumber());
    }
}
