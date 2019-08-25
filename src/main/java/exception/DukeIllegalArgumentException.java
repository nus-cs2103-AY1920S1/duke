package com.leeyiyuan.exception;

/** 
 * Represents an exception indicating that a disallowed value has been used as an argument. 
 */
public class DukeIllegalArgumentException extends DukeException {

    /**
     * Constructs a new exception with the specified detail message.
     *
     * @param message The detail message.
     */
    public DukeIllegalArgumentException(String message) {
        super(message);
    }
}
