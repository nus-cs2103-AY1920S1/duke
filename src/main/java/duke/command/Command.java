package duke.command;

import duke.exception.DukeException;
import duke.task.TaskList;
import duke.util.Ui;

/**
 * Abstract Command class, for inheritance of various commands.
 */
public abstract class Command {
    /**
     * Executes the command.
     *
     * @param taskList List of tasks for operation of command.
     * @param ui Ui for command to write output.
     */
    public abstract void execute(TaskList taskList, Ui ui) throws DukeException;

    /**
     * Checks if the command should cause the application to exit.
     *
     * @return true if command should cause the application to exit, or false otherwise.
     */
    public abstract boolean isExit();
}
