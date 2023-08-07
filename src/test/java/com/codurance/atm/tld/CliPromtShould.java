package com.codurance.atm.tld;

import com.codurance.atm.infrastructure.CliPrompt;
import com.codurance.atm.infrastructure.Printer;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class CliPromtShould {

    private String expectedMenu = "Account number {0}, balance {1}\n" +
        "1. $10\n" +
        "2. $50\n" +
        "3. $100\n" +
        "4. Other\n" +
        "5. Back\n" +
        "Please choose option [5]: ";

    @Test
    void show_() {
        ArgumentCaptor<String> arg = ArgumentCaptor.forClass(String.class);
        InputStream inputStream = new ByteArrayInputStream("1".getBytes());
        Printer printer = mock();
        CliPrompt cliPrompt = new CliPrompt(inputStream, printer);

        String option = cliPrompt.withdrawScreenMenu();

        verify(printer).prompt(arg.capture());
        assertEquals("1", option);
        assertEquals(expectedMenu, arg.getValue());
    }
}
