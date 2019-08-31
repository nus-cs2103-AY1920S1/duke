package duke.command;

import duke.exception.DukeException;
import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;

/**
 * Represents an executable action. A <code>Command</code> object corresponds to
 * an action to execute as specified by Duke's user.
 * Determines if program should exit.
 */
public abstract class Command {

    protected boolean isExit;

    /**
     * Performs operations of type Create, Read, Update, Delete and Exit
     * on Task objects and directs action of file storage and user interaction.
     *
     * @param taskList  List of Task objects.
     * @param ui Interface for user interaction.
     * @param storage Interface for read and write operations on file.
     * @throws DukeException  Error while executing command.
     */
    public abstract String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException;

    public Command() {
        isExit = false;
    }

    public boolean isExit() {
        return isExit;
    }

}
