package com.codurance.atm.screens;

import com.codurance.atm.account.Account;
import com.codurance.atm.account.AccountService;
import com.codurance.atm.infrastructure.CliPrompt;

import java.text.MessageFormat;

public class WithdrawScreen implements Screen {

    private static final String WITHDRAW_SCREEN_MESSAGE = "Your current balance is {0}";
    private final CliPrompt cliPrompt;
    private final AccountService accountService;
    private Account account;

    public WithdrawScreen(CliPrompt cliPrompt, Account account, AccountService accountService) {
        this.cliPrompt = cliPrompt;
        this.account = account;
        this.accountService = accountService;
    }

    @Override
    public Screen show() {
        String option = cliPrompt.withdrawScreenMenu();
        if (option.equals("1")) {
            this.account = this.accountService.withdraw(account.accountNumber(), 50);

            cliPrompt.promptGenericMessage(MessageFormat.format(WITHDRAW_SCREEN_MESSAGE, this.account.balance()));
        }

        return null;
    }

    @Override
    public ScreenEnum screenName() {
        return ScreenEnum.WITHDRAW;
    }
}
