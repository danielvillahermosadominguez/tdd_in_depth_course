package com.codurance.atm.account;

public class Account {
    private AccountNumber accountNumber;
    private Integer balance;

    public Account(AccountNumber accountNumber, Integer balance) {

        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public AccountNumber accountNumber() {
        return accountNumber;
    }

    public String balance() {
        return balance.toString();
    }
}
