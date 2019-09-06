package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;


public abstract class Command {
    /**
     * Tell whether the duke.command is exit duke.command.
     *
     * @return true when it is.
     */
    public abstract boolean isExit();

    /**
     * Execute the duke.command according to the duke.command type.
     *
     * @param taskList The list of duke.tasks maintained in duke.Duke
     * @param ui       Ui module
     * @param storage  in charge of loading and saving the duke.tasks
     * @throws DukeException when the duke.command is invalid
     */
    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException;
}
