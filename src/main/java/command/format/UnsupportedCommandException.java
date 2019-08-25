package com.leeyiyuan.command.format;

/** 
 * Represents an exception indicating that a Command text is unsupported. 
 */
public class UnsupportedCommandException extends CommandParseException {

    /**
     * Constructs a new exception with the specified detail message.
     *
     * @param message The detail message.
     */
    public UnsupportedCommandException(String message) {
        super(message);
    }
}
