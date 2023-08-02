package com.codurance.atm.tld;

import com.codurance.atm.account.Account;
import com.codurance.atm.account.AccountService;
import com.codurance.atm.infrastructure.CliPrompt;
import com.codurance.atm.screens.Screen;
import com.codurance.atm.screens.ScreenEnum;
import com.codurance.atm.screens.WelcomeScreen;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class WelcomeScreenShould {
    @Test
    void follow_the_process_of_login_and_returns_the_transaction_screen() {
        //Arrange
        CliPrompt cliPrompt = mock(CliPrompt.class);
        Account expectedAccount = new Account("123456", 1000);
        AccountService accountService = mock(AccountService.class);
        WelcomeScreen welcomeScreen = new WelcomeScreen(cliPrompt, accountService);
        when(cliPrompt.accountNumber()).thenReturn("123456");
        when(accountService.findBy("123456", "789123")).thenReturn(expectedAccount);
        when(cliPrompt.pin()).thenReturn("789123");

        //Act
        Screen screen = welcomeScreen.show();

        //Assert
        assertEquals(ScreenEnum.TRANSACTION, screen.screenName());
    }

//    @Test
//    void show_a_error_message_when_account_number_contain_chars() {
//        //Arrange
//        CliPrompt cliPrompt = mock(CliPrompt.class);
//        Account expectedAccount = new Account("123456", 1000);
//        AccountService accountService = mock(AccountService.class);
//        WelcomeScreen welcomeScreen = new WelcomeScreen(cliPrompt, accountService);
//        when(cliPrompt.accountNumber()).thenReturn("12345D");
//        when(accountService.findBy("123456", "789123")).thenReturn(expectedAccount);
//        when(cliPrompt.pin()).thenReturn("789123");
//
//        //Act
//        Thread thread = new Thread(() -> welcomeScreen.show());
//        thread.setDaemon(true);
//        thread.start();
//
//        //Assert
//        await()
//            .timeout(10, TimeUnit.SECONDS)
//            .untilAsserted(
//                () -> verify(cliPrompt).promptGenericMessage("Account Number should only contain numbers\n")
//            );
//
//        thread.interrupt();
//    }
}
