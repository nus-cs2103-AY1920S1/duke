package customexceptions;

import customexceptions.DukeException;

public class DeadlineDetailsEmptyException extends DukeException {
    public DeadlineDetailsEmptyException(String message) {
        super(message);
    }
}
