package com.leeyiyuan.ui;


import com.leeyiyuan.exception.DukeException;

/** 
 * Represents an exception indicating an error in the Ui.
 */
public class UiException extends DukeException {

    /**
     * Constructs a new exception with the specified detail message.
     *
     * @param message The detail message.
     */
    public UiException(String message) {
        super(message);
    }
}
