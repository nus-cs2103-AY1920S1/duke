package customexceptions;

import customexceptions.DukeException;

public class TaskNotFoundException extends DukeException {
    public TaskNotFoundException(String message) {
        super(message);
    }
}
