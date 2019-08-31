package seedu.duke.exception;

import seedu.duke.Ui;

public class DukeIndexOutOfBoundException extends DukeException {

    private String errorMessage;

    public DukeIndexOutOfBoundException(int input, int max) {
        super("");
        this.errorMessage = String.format(
                "There is no task number #%d. Please enter a range between 1 and %d.",
                input, max);
    }

    @Override
    public void printMessage() {
        Ui.printBlock(this.errorMessage);
    }

}
