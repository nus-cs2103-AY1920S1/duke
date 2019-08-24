package com.leeyiyuan.command;

import com.leeyiyuan.command.CommandExecuteException;

public class AbortException extends CommandExecuteException {

    public AbortException(String message) {
        super(message);
    }

}
