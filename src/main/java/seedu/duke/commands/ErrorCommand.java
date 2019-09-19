package seedu.duke.commands;

import seedu.duke.exceptions.DukeException;
import seedu.duke.storage.TaskList;

/**
 * Abstraction of the Error Command to marshal errors thrown during identification of the command
 * in {@link seedu.duke.helpers.Parser} to the executing thread to report the error.
 * NOTE: This Command is system-generated and cannot be used like a regular CLI command i.e error args...
 */
public class ErrorCommand extends Command {
    private DukeException exception;

    public ErrorCommand(DukeException dukeException) {
        this.exception = dukeException;
    }

    /**
     * Re-throws the error up the chain to be handled by the executing thread.
     * @param tasks The current TaskList instance.
     * @throws DukeException Re-throws the {@code exception}.
     */
    @Override
    public String execute(TaskList tasks) throws DukeException {
        throw exception;
    }
}
