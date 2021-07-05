package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;


public abstract class TaskCommand {
    protected static final String outputInvalidTask = "It's an invalid task";

    /**
     * Tells whether the duke.command is exit duke.command.
     *
     * @return true when it is.
     */
    public abstract boolean isExit();

    /**
     * Executes the duke.command according to the duke.command type.
     *
     * @param taskList The list of duke.tasks maintained in duke.Duke
     * @param ui       Ui module
     * @param storage  in charge of loading and saving the duke.tasks
     * @throws DukeException when the duke.command is invalid
     */
    public abstract String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException;
}
