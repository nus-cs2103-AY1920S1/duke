package com.leeyiyuan.command;

/** 
 * Represents an exception indicating that an abort signal was raised. 
 */
public class AbortException extends CommandExecuteException {

    /**
     * Constructs a new exception with the specified detail message.
     *
     * @param message The detail message.
     */
    public AbortException(String message) {
        super(message);
    }
}
