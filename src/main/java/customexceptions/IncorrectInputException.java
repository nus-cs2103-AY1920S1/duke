package customexceptions;

import customexceptions.DukeException;

public class IncorrectInputException extends DukeException {
    public IncorrectInputException(String message) {
        super(message);
    }
}
