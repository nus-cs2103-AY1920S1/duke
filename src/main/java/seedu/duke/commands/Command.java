package seedu.duke.commands;

import seedu.duke.TaskList;
import seedu.duke.exceptions.DukeException;

public abstract class Command {
    protected TaskList taskList;
    protected String arg;

    Command(String arg, TaskList taskList) {
        this.arg = arg;
        this.taskList = taskList;
    }

    /**
     * Executes this command.
     * @return The response.
     */
    public abstract String execute() throws DukeException;

    public boolean isExitCommand() {
        return false;
    }
}
