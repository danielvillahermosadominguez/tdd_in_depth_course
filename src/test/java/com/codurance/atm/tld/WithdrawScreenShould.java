package com.codurance.atm.tld;

import com.codurance.atm.account.Account;
import com.codurance.atm.account.AccountService;
import com.codurance.atm.infrastructure.CliPrompt;
import com.codurance.atm.screens.WithdrawScreen;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class WithdrawScreenShould {

    public static final String ACCOUNT_NUMBER = "123456";

    @Test
    void remove_50_dollars() {
        int moneyTowithdraw = 50;
        CliPrompt cliPrompt = mock(CliPrompt.class);
        Account account = new Account(ACCOUNT_NUMBER, 1000);
        AccountService accountService = mock(AccountService.class);
        WithdrawScreen withdrawScreen = new WithdrawScreen(cliPrompt, account, accountService);
        when(cliPrompt.withdrawScreenMenu()).thenReturn("1");
        when(accountService.withdraw(ACCOUNT_NUMBER, moneyTowithdraw)).thenReturn(new Account(ACCOUNT_NUMBER, 995));

        withdrawScreen.show();

        verify(accountService).withdraw(ACCOUNT_NUMBER, moneyTowithdraw);
        verify(cliPrompt).promptGenericMessage("Your current balance is 995");
    }
}