package com.codurance.atm.screens;

import com.codurance.atm.account.Account;
import com.codurance.atm.account.AccountService;
import com.codurance.atm.infrastructure.CliPrompt;

public class TransactionScreen implements Screen {
    private final CliPrompt cliPrompt;
    private final AccountService accountService;
    private Account account;

    public TransactionScreen(CliPrompt cliPrompt, Account account, AccountService accountService) {
        this.cliPrompt = cliPrompt;
        this.account = account;
        this.accountService = accountService;
    }

    @Override
    public Screen show() {
        cliPrompt.transactionScreenMenu(account.accountNumber(), account.balance());
        return new WithdrawScreen(cliPrompt, account, this.accountService);
    }

    @Override
    public ScreenEnum screenName() {
        return ScreenEnum.TRANSACTION;
    }
}
