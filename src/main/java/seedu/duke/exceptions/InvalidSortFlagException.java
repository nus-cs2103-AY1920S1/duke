package seedu.duke.exceptions;

import seedu.duke.commands.SortCommand;

public class InvalidSortFlagException extends DukeException {
    public InvalidSortFlagException() {
        super("Sort command should be in the form of 'sort FLAG'.\nAvailable flags: " + SortCommand.getModes());
    }
}
