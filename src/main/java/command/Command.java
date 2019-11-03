package command;

import main.Archive;
import main.DukeException;
import main.Storage;
import main.TaskList;
import main.Ui;

/**
 * Abstract class to deal with handling commands.
 */
public abstract class Command {

    private boolean isExit;

    /**
     * Creates a Command object to handle commands received from ui.
     */
    public Command() {
        this.isExit = false;
    }

    /**
     * Executes a full command if valid.
     *
     * @param tasks     The existing task list
     * @param ui        The Ui object which interacts with the current user
     * @param storage   The Storage object which reads and writes to a specified file
     * @param archive   The Archive object for archiving purposes
     * @return          The message to be displayed upon successful execution
     * @throws DukeException If command is invalid
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage, Archive archive) throws DukeException;

    /**
     * Indicates if the command is an exit command.
     *
     * @return The boolean to indicate if the ui should exit.
     */
    public boolean isExit() {
        return isExit;
    }

    /**
     * Sets the exit boolean to true.
     */
    public void canExit() {
        this.isExit = true;
    }
}
