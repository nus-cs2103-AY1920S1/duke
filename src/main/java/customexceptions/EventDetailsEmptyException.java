package customexceptions;

import customexceptions.DukeException;

public class EventDetailsEmptyException extends DukeException {
    public EventDetailsEmptyException(String message) {
        super(message);
    }
}
