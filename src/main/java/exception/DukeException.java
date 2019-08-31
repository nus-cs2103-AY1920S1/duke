package seedu.duke.exception;

import seedu.duke.Ui;

public class DukeException extends Exception {

    String message;

    public DukeException(String message) {
        super();
        this.message = message;
    }

    public void printMessage() {
        Ui.printBlock(message);
    }


}
