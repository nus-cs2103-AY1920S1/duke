package duke;

import duke.DukeException;

public class MissingInputException extends DukeException {

    String message = "☹ OOPS!!! ";

    public MissingInputException(String taskType) {
        super(taskType);
    }

    @Override
    public String errorMessage() {
        message += String.format("The description of a %s cannot be empty.", taskType);
        return message;
    }

}
