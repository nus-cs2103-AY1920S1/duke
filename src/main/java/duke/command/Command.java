package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * An abstract class that represents a command.
 */
public abstract class Command {
    /** To indicate if the commmand is an exit command */
    protected boolean isExit;

    /**
     * Abstract method to execute the respective command
     * @param tasks
     * @param ui
     * @param storage
     * @throws DukeException
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;
    public Command(){
        isExit = false;
    }
    public boolean isExit(){
        return isExit;
    }
}
