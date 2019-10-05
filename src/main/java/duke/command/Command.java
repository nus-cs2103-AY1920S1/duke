package duke.command;

import duke.task.TaskList;
import duke.exception.DukeException;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Command is the abstract base class for all commands in <code>Duke</code> application
 * which allows for the user to instruct what is to be done to the current task list.
 *
 * @author Clarence Koh
 * @version 1.0
 * @since 29th August 2019
 */
public abstract class Command {

    /**
     * Determines whether the user intends to terminate <code>Duke</code> application in the
     * command.
     */
    protected boolean isExit;

    /**
     * Class constructor for a default command which does not terminate the <code>Duke</code> application.
     */
    public Command() {
        this.isExit = false;
    }

    /**
     * This method defines what the user wants to do in the <code>Duke</code> application, such as
     * delete a task, mark a task as completed, or add a new task to the task list.
     *
     * @param tasks The task lists which contains all the user added tasks.
     * @param ui The user interface which deals with user input and interaction.
     * @param storage The storage to load and save task data into the output file.
     * @throws DukeException If there is a problem with data processing, loading or saving.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    /**
     * This method checks if the command is one that requires the closing of <code>Duke</code>
     * application.
     *
     * @return <code>true</code> if the command calls for the termination of the application.
     */
    public boolean isExit() {
        return this.isExit;
    }
}