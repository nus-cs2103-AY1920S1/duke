package com.exceptions;

/**
 * Thrown when data in text file in wrong format.
 */
public class DukeStorageException extends DukeException {
    public DukeStorageException(String message) {
        super(message);
    }
}