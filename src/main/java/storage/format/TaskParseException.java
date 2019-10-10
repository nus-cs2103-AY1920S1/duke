package com.leeyiyuan.storage.format;


import com.leeyiyuan.exception.DukeException;

/** 
 * Represents an exception indicating an error in parsing a task.
 */
public class TaskParseException extends DukeException {

    /**
     * Constructs a new exception with the specified detail message.
     *
     * @param message The detail message.
     */
    public TaskParseException(String message) {
        super(message);
    }
}
