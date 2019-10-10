package com.leeyiyuan.exception;

/** 
 * Represents an exception indicating an error within the Duke program. 
 */
public class DukeException extends Exception {

    /**
     * Constructs a new exception with the specified detail message.
     *
     * @param message The detail message.
     */
    public DukeException(String message) {
        super(message);
    }
}
