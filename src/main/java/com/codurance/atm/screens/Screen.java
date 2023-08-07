package com.codurance.atm.screens;

import com.codurance.atm.account.NotValidAccountNumber;

public interface Screen {
    Screen show() throws NotValidAccountNumber;

    ScreenEnum screenName();
}
