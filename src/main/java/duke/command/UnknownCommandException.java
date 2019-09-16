package duke.command;

import duke.DukeException;

public class UnknownCommandException extends DukeException {
    public UnknownCommandException(String message) {
        super(message);
    }

    public UnknownCommandException(String message, Throwable cause) {
        super(message, cause);
    }
}
