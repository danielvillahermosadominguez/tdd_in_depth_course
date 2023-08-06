package com.codurance.atm.account;

public class NotValidAccountNumber extends Throwable {
    public NotValidAccountNumber(String message) {
        super(message);
    }
}
