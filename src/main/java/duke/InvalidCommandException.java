package duke;

import duke.DukeException;

public class InvalidCommandException extends DukeException {

    String message = "☹ OOPS!!! ";

    public InvalidCommandException() {}

    @Override
    public String errorMessage() {
        this.message += "I'm sorry, but I don't know what that means :-(";
        return this.message;
    }
}
