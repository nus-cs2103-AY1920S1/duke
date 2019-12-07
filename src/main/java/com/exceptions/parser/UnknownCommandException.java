package com.exceptions.parser;

import com.exceptions.DukeException;

/**
 * Thrown when user input (first word) is unrecognised/not in program.
 */
public class UnknownCommandException extends DukeException {
    public UnknownCommandException() {
        super("I'm sorry, but I don't know what that means :-(");
    }
}
