package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * An abstract class that represents a command.
 */
public abstract class Command {

    /**
     * To indicate if the command is an exit command.
     */
    protected boolean isExit;

    /**
     * Abstract method to execute the respective command.
     *
     * @param tasks   The user's current TaskList
     * @param ui      The ui currently being used by the user
     * @param storage The storage object being used by the user
     * @throws DukeException An error trying to carry out the command
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    /**
     * Constructor for command will set isExit to false by default.
     */
    public Command() {
        isExit = false;
    }

    /**
     * Check if a command is an exit command.
     *
     * @return whether the command is an exit command
     */
    public boolean isExit() {
        return isExit;
    }
}
