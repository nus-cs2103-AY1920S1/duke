package com.leeyiyuan.storage;


import com.leeyiyuan.exception.DukeException;

/** 
 * Represents an exception indicating an error within a Storage.
 */
public class StorageException extends DukeException {

    /**
     * Constructs a new exception with the specified detail message.
     *
     * @param message The detail message.
     */
    public StorageException(String message) {
        super(message);
    }
}
