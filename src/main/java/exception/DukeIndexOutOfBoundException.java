package seedu.duke.exception;

import seedu.duke.Ui;

public class DukeIndexOutOfBoundException extends DukeException {

    private String errorMessage;

    /**
     * Creates an exception for when the index specified is out of bound of the size of array.
     * @param input Input passed in by the user.
     * @param max Maximum size of the array (to be passed in by the program)
     */
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
