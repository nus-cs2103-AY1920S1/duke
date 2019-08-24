package com.leeyiyuan.command;

public class AbortException extends CommandExecuteException {

    public AbortException(String message) {
        super(message);
    }
}
