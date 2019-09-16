package duke.storage;

import duke.DukeException;

public abstract class SerializerException extends DukeException {
    public SerializerException(String message) {
        super(message);
    }

    public SerializerException(String message, Throwable cause) {
        super(message, cause);
    }
}
