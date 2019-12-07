package com.util.ui;

import com.util.StaticStrings;

public class CLIUi extends Ui {

    public CLIUi() {
        super();
    }

    @Override
    public void showMessage(String message) {
        assert message.substring(0, 3) == StaticStrings.INDENT : "message should be indented";
        System.out.println(StaticStrings.BORDER);
        System.out.println(message);
        System.out.println(StaticStrings.BORDER);
    }

}
