package seedu.duke.commands;

import seedu.duke.TaskList;
import seedu.duke.exceptions.DukeException;

public class SortCommand extends Command {
    public SortCommand(String arg, TaskList taskList) {
        super(arg, taskList);
    }

    @Override
    public String execute() throws DukeException {
        return sort(arg);
    }

    /**
     * Sorts the {@code taskList}.
     * @param arg The flags.
     * @return The response.
     */
    private String sort(String arg) {
        return null;
    }
}
