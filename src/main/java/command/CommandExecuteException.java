package com.leeyiyuan.command;


import com.leeyiyuan.exception.DukeException;

/** 
 * Represents an exception indicating an error in executing a Command. 
 */
public class CommandExecuteException extends DukeException {

    /**
     * Constructs a new exception with the specified detail message.
     *
     * @param message The detail message.
     */
    public CommandExecuteException(String message) {
        super(message);
    }
}
