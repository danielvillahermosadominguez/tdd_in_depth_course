package com.codurance.atm.tld;

import com.codurance.atm.account.Account;
import com.codurance.atm.account.AccountNumber;
import com.codurance.atm.account.AccountService;
import com.codurance.atm.account.NotValidAccountNumber;
import com.codurance.atm.infrastructure.CliPrompt;
import com.codurance.atm.screens.Screen;
import com.codurance.atm.screens.ScreenEnum;
import com.codurance.atm.screens.WelcomeScreen;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class WelcomeScreenShould {
    @Test
    void follow_the_process_of_login_and_returns_the_transaction_screen() throws NotValidAccountNumber {
        //Arrange
        CliPrompt cliPrompt = mock(CliPrompt.class);
        String accountNumberText = "123456";
        AccountNumber accountNumber = new AccountNumber(accountNumberText);
        Account expectedAccount = new Account(accountNumber, 1000);
        AccountService accountService = mock(AccountService.class);
        WelcomeScreen welcomeScreen = new WelcomeScreen(cliPrompt, accountService);
        when(cliPrompt.accountNumber()).thenReturn(accountNumberText);
        when(accountService.findBy(any(), any())).thenReturn(expectedAccount);
        when(cliPrompt.pin()).thenReturn("789123");

        //Act
        Screen screen = welcomeScreen.show();

        //Assert
        assertEquals(ScreenEnum.TRANSACTION, screen.screenName());
    }
}
