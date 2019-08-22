package seedu.duke;

import seedu.duke.PrettyPrint;

public class DukeException extends Exception {

    String message;

    public DukeException(String message) {
        super();
        this.message = message;
    }

    public void printMessage() {
        PrettyPrint.printBlock(message);
    }


}
