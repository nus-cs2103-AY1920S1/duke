package seedu.duke.exception;

public class DukeInvalidSortParameterException extends DukeException {

    /**
     * Creates an exception when the parameter passed into the sort command is invalid (Insufficient, not allowed, wrong, etc)
     */
    public DukeInvalidSortParameterException() {
        super("The parameters for the sort command are invalid. Please input in the following format:\n"
            + "sort (name/date) (ascending/descending)\n"
            + "Example: sort name ascending");
    }

}
