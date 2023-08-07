package com.codurance.atm.account;

public class Account {
    private AccountNumber accountNumber;
    private AccountBalance balance;

    public Account(AccountNumber accountNumber, AccountBalance balance) {

        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public AccountNumber accountNumber() {
        return accountNumber;
    }

    public String balance() {
        return balance.getValue().toString();
    }
}
