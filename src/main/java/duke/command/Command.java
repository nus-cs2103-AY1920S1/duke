package duke.command;

import duke.exception.DukeException;
import duke.util.Storage;
import duke.task.TaskList;
import duke.util.Ui;

/**
 * Abstract class representing a template for Command sub-classes to implement
 * and inherit from. Represents a command issued by the user to Duke.
 */
public abstract class Command {
    protected Storage storage;
    protected TaskList taskList;
    protected Ui ui;

    /**
     * Passes in essential Duke util objects for the Commands to act on.
     *
     * @param storage a Storage object with disk saving and loading capabilities
     * @param taskList a TaskList object representing list of tasks
     * @param ui a Ui object for displaying output to screen
     */
    public Command initialize(Storage storage, TaskList taskList, Ui ui) {
        this.storage = storage;
        this.taskList = taskList;
        this.ui = ui;
        return this;
    }

    /**
     * Executes actions that are specific to each Command sub-class. To be implemented
     * by sub-classes. As a general rule, initialize() should always be called
     * to update the relevant data in the Command sub-class object before
     * any calls to execute() are made.
     *
     * @return result feedback of the command to be printed to the user
     * @throws DukeException for generic recoverable errors
     */
    public abstract String execute() throws DukeException;
}
