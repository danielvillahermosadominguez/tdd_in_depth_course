package com.codurance.atm.account;

public class AccountService {
    public Account findBy(AccountNumber accountNumber, String pin) {
        return new Account(accountNumber, new AccountBalance(0));
    }

    public Account withdraw(AccountNumber accountNumber, int quantity) {
        throw new UnsupportedOperationException();
    }
}
