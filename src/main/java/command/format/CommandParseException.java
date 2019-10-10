package com.leeyiyuan.command.format;


import com.leeyiyuan.exception.DukeException;

/** 
 * Represents an exception indicating an error in parsing a Command text. 
 */
public class CommandParseException extends DukeException {

    /**
     * Constructs a new exception with the specified detail message.
     *
     * @param message The detail message.
     */
    public CommandParseException(String message) {
        super(message);
    }
}
