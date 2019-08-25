package duke.command;

import duke.DukeException;

public class DukeUnknownCommandException extends DukeException {
    public DukeUnknownCommandException() {
        super("I'm sorry. I don't know what that means :c");
    }
}
