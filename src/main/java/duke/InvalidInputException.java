package duke;

import duke.DukeException;

public class InvalidInputException extends DukeException {

    String message = "☹ OOPS!!! ";

    @Override
    public String errorMessage() {
        message += "I'm sorry, but I don't know what your input means :-(";
        return message;
    }
}
