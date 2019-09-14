package customexceptions;

import customexceptions.DukeException;

public class TodoEmptyDescriptionException extends DukeException {
    public TodoEmptyDescriptionException(String message) {
        super(message);
    }
}

