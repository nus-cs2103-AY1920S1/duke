package com.leeyiyuan.storage.format;


import com.leeyiyuan.exception.DukeException;

/** 
 * Represents an exception indicating an error in formatting a task.
 */
public class TaskFormatException extends DukeException {

    /**
     * Constructs a new exception with the specified detail message.
     *
     * @param message The detail message.
     */
    public TaskFormatException(String message) {
        super(message);
    }
}
