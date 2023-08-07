package com.codurance.atm.acceptance;

import com.codurance.atm.AtmSimulator;
import com.codurance.atm.account.Account;
import com.codurance.atm.account.AccountService;
import com.codurance.atm.infrastructure.CliPrompt;
import com.codurance.atm.infrastructure.ConsolePrinter;
import com.codurance.atm.screens.WelcomeScreen;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AcceptanceTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    private String expectedOutput = "Enter Account Number: Enter PIN: Account number 123456, balance 100\n" +
        "1. Withdraw\n" +
        "2. Fund Transfer\n" +
        "3. Exit\n" +
        "Please choose option [3]: Account number {0}, balance {1}\n" +
        "1. $10\n" +
        "2. $50\n" +
        "3. $100\n" +
        "4. Other\n" +
        "5. Back\n" +
        "Please choose option [5]: Your current balance is 50";

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    private String getFormattedOutput() {
        return outContent.toString().replace("\r", "");
    }

    @Test
    void acceptance_test_of_the_course() {
        //Given there's an Account with number 123456, PIN 123456 and balance 100
        InputStream sysInBackup = System.in;
        System.setIn(new ByteArrayInputStream("123456\n123456\n1\n1\n".getBytes()));
        Account initialAccount = new Account("123456", 100);
        Account expectedAccount = new Account("123456", 50);
        AccountService accountService = mock();
        when(accountService.findBy("123456", "123456")).thenReturn(initialAccount);
        when(accountService.withdraw("123456", 50)).thenReturn(expectedAccount);
        //And I entered 123456 as Account number and 123456 as PIN
        AtmSimulator atmSimulator = new AtmSimulator(
            new WelcomeScreen(
                new CliPrompt(System.in, new ConsolePrinter()),
                accountService));
        atmSimulator.showScreen();
        //When I withdraw 50

        //Then I should see the message "Your current balance is 50".
        assertEquals(expectedOutput, getFormattedOutput());
        System.setIn(sysInBackup);
    }
}
