package com.exceptions.parser;

import com.exceptions.DukeException;

//Empty description after command (todo, event, deadline, list, done)

/**
 * Thrown if Empty description after command
 * (e.g. todo, event, deadline, list, done)
 */
public class EmptyDescriptionException extends DukeParserException {

    public EmptyDescriptionException(String command) {
        super("The description of " +
                command +
                " cannot be empty.");
    }
}