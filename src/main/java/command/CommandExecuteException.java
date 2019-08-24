package com.leeyiyuan.command;


import com.leeyiyuan.exception.DukeException;

public class CommandExecuteException extends DukeException {

    public CommandExecuteException(String message) {
        super(message);
    }
}
