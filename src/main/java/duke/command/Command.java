package duke.command;

import duke.util.exception.DukeException;
import duke.task.TaskList;
import duke.util.Ui;

/**
 * Represents commands requested by user and related operations,
 * such as executing the command.
 */
public interface Command {

    /**
     * Executes the command, by interacting with tasks and UI.
     * @param tasks List of tasks.
     * @param ui UI to display to the user.
     * @throws DukeException Application-specific exception thrown during execution.
     */
    void execute(TaskList tasks, Ui ui) throws DukeException;
    // implementation varies for each subclass of Command

    /**
     * Returns boolean indicating if command entered was "exit", false by default.
     * @return boolean indicating if command entered was "exit".
     */
    default boolean isExit() {
        return false;
    }
}
