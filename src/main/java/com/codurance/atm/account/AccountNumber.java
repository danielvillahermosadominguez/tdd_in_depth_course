package com.codurance.atm.account;

public class AccountNumber {
    public AccountNumber(String accountNumber) throws NotValidAccountNumber {
        if (!accountNumber.matches("(\\d)+")) {
            throw new NotValidAccountNumber("Account Number should only contain numbers");
        }

        if (accountNumber.length() > 6 || accountNumber.length() < 6) {
            throw new NotValidAccountNumber("Account Number should have 6 digits length");
        }
    }
}
