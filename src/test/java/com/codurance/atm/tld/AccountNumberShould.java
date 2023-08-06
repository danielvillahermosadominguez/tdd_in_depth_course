package com.codurance.atm.tld;

import com.codurance.atm.account.AccountNumber;
import com.codurance.atm.account.NotValidAccountNumber;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AccountNumberShould {

    @ParameterizedTest
    @ValueSource(strings = {
        "a1232321",
        "12323e-"
    })
    void throw_an_exception_if_not_only_contain_numbers(String accountNumber) {
        NotValidAccountNumber ex = assertThrows(NotValidAccountNumber.class, () -> {
            new AccountNumber(accountNumber);
        });
        assertEquals(
            "Account Number should only contain numbers",
            ex.getMessage()
        );
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "1232321",
        "12323212"
    })
    void throw_an_exception_if_have_more_than_6_digits_length(String accountNumber) {
        NotValidAccountNumber ex = assertThrows(NotValidAccountNumber.class, () -> {
            new AccountNumber(accountNumber);
        });
        assertEquals(
            "Account Number should have 6 digits length",
            ex.getMessage()
        );
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "1"
    })
    void throw_an_exception_if_have_less_than_6_digits_length(String accountNumber) {
        NotValidAccountNumber ex = assertThrows(NotValidAccountNumber.class, () -> {
            new AccountNumber(accountNumber);
        });
        assertEquals(
            "Account Number should have 6 digits length",
            ex.getMessage()
        );
    }

   /* @Test
    void throw_an_exception_if_have_less_than_6_digits_length() {

    }*/
}
