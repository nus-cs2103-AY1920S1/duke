package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.Storage;
import duke.DukeException;

/**
 * Abstract Command class that contains execute() for
 * interacting with TaskList, Ui and Storage objects based on input.
 */
public abstract class Command {
    protected String fullCommand;

    /**
     * Public method to initialize Command objects.
     *
     * @param fullCommand Input entered by user.
     * @return Command object initialized by constructor.
     */
    public abstract Command clone(String fullCommand);

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