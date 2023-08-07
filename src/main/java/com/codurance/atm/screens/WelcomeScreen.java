package com.codurance.atm.screens;

import com.codurance.atm.account.*;
import com.codurance.atm.infrastructure.CliPrompt;

public class WelcomeScreen implements Screen {
    private final CliPrompt cliPrompt;
    private final AccountService accountService;

    private String pin;
    private AccountNumber accountNumber;

    public WelcomeScreen(CliPrompt cliPrompt, AccountService accountService) {
        this.cliPrompt = cliPrompt;
        this.accountService = accountService;
    }

    public Screen show() throws NotValidAccountNumber {
        Account account = askAccountNumber();
        return new TransactionScreen(cliPrompt, account, accountService);
    }

    @Override
    public ScreenEnum screenName() {
        return ScreenEnum.WELCOME_SCREEN;
    }

    private Account askAccountNumber() throws NotValidAccountNumber {
        boolean isInvalid = true;
        do {
            try {
                accountNumber = new AccountNumber(cliPrompt.accountNumber());
                isInvalid = false;
            } catch (NotValidAccountNumber exception) {
                cliPrompt.promptGenericMessage(exception.getMessage() + "\n");
            }
        } while (isInvalid);
        return askPin();
    }

    private boolean isInvalid(String accountNumber) {
        if (!accountNumber.matches("\\d+")) {
            cliPrompt.promptGenericMessage("Account Number should only contain numbers\n");
            return true;
        }
        if (accountNumber.length() != 6) {
            cliPrompt.promptGenericMessage("Account Number should have 6 digits length\n");
            return true;
        }
        return false;
    }

    private Account askPin() throws NotValidAccountNumber {
        do {
            pin = cliPrompt.pin();
        } while (isInvalidPIN(pin));

        Account account = findAccount(accountNumber, pin);
        if (account == null) show(); //start over
        return account;
    }

    private Account findAccount(AccountNumber accountNumber, String pin) {
        try {
            return accountService.findBy(accountNumber, pin);
        } catch (InvalidAccountPin e) {
            cliPrompt.promptGenericMessage("Invalid Account Number/PIN\n");
        }
        return null;
    }

    private boolean isInvalidPIN(String pin) {
        if (!pin.matches("\\d+")) {
            cliPrompt.promptGenericMessage("PIN should only contain numbers\n");
            return true;
        }
        if (pin.length() != 6) {
            cliPrompt.promptGenericMessage("PIN should have 6 digits length\n");
            return true;
        }
        return false;
    }
}
