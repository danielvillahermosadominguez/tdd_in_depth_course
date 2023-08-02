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
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AcceptanceTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

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
        System.setIn(new ByteArrayInputStream("123456\n123456\n1\n".getBytes()));
        Account expectedAccount = new Account("123456", 100);
        AccountService accountService = mock();
        when(accountService.findBy("123456", "123456")).thenReturn(expectedAccount);
        //And I entered 123456 as Account number and 123456 as PIN
        AtmSimulator atmSimulator = new AtmSimulator(
            new WelcomeScreen(
                new CliPrompt(System.in, new ConsolePrinter()),
                accountService));
        atmSimulator.showScreen();
        //When I withdraw 50

        //Then I should see the message "Your current balance is 50".
        assertEquals("Your current balance is 50", getFormattedOutput());
        System.setIn(sysInBackup);
    }

    @Test
    void test_of_input_with_BytesArrayInputStream1() {

        InputStream sysInBackup = System.in;

        System.setIn(new ByteArrayInputStream("123456\n123456\n".getBytes()));
        Scanner scanner = new Scanner(System.in);
        String result = scanner.next();
        String result2 = scanner.next();
        assertEquals("123456", result);
        assertEquals("123456", result2);
        System.setIn(sysInBackup);
    }

    @Test
    void test_of_input_with_BytesArrayInputStream2() {

        InputStream sysInBackup = System.in;

        System.setIn(new ByteArrayInputStream("123456\naaa\n".getBytes()));
        Scanner scanner = new Scanner(System.in);
        String result = scanner.next();
        System.setIn(new ByteArrayInputStream("123456\n".getBytes()));
        String result2 = scanner.next();
        assertEquals("123456", result);
        assertEquals("123456", result2);
        System.setIn(sysInBackup);
    }
}
