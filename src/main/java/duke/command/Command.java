package duke.command;

import duke.task.TaskList;
import duke.util.DukeException;
import duke.util.History;
import duke.util.Storage;
import duke.util.Ui;

/**
 * An abstract Command to be overridden by more specific Command types.
 */
public abstract class Command {

    /**
     * Executes the command in a manner specific to the type of command.
     * This method is abstract and should be overridden in child classes.
     * @param tasks The TaskList containing the user's added Tasks.
     * @param ui The UI to interact with the user by printing instructions/messages.
     * @param storage Storage to use for loading/saving tasks from/to a file on the hard disk.
     * @param history History of commands for the current session.
     * @return Duke's response to the Command as a String.
     * @throws DukeException If the execution of the command fails.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage, History history) throws DukeException;

    /**
     * Checks whether the command is the command to exit the app.
     * @return True if the command is the exit command. False otherwise.
     */
    public boolean isExit() {
        return false;
    }
}
