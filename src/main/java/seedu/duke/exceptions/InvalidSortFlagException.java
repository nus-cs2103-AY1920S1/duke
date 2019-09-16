package seedu.duke.exceptions;

import seedu.duke.commands.SortCommand;

public class InvalidSortFlagException extends DukeException {
    public InvalidSortFlagException() {
        super("Sort command should be in the form of 'sort [Mode]'.\nAvailable modes: " + SortCommand.getModes());
    }
}
