package seedu.duke;

import seedu.duke.PrettyPrint;

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
        PrettyPrint.printBlock(this.errorMessage);
    }

}
