package duke;

import duke.DukeException;

public class InvalidItemException extends DukeException {

    String message = "☹ OOPS!!! ";

    public InvalidItemException() {
    }

    @Override
    public String errorMessage() {
        message += "This item does not exist on the list";
        return message;
    }

}
