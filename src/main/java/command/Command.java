package duke.command;

import duke.task.TaskList;
import duke.util.Ui;
import duke.util.Storage;
import duke.exception.DukeException;

/**
 * Abstract Command class that contains execute() for
 * interacting with TaskList, Ui and Storage objects based on input.
 */
public abstract class Command {
    /**
     * Interacts with TaskList, Ui and Storage objects based on input.
     *
     * @param tasks TaskList to add Task to.
     * @param ui Ui for printing responses to the console.
     * @param storage Storage that stores the modified TaskList.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    /**
     * Returns boolean to initiate exit of program.
     * 
     * @return Boolean to initiate exit of program.
     */
    public abstract boolean isExit();
}