package com.duke.exception;

/**
 * Represents RedundantOperationException. Thrown when there is redundant operation.
 */
public class RedundantOperationException extends DukeException {

    /**
     * Constructs a RedundantOperationException object.
     *
     * @param message Exception message.
     */
    public RedundantOperationException(String message) {
        super(message);
    }
}
