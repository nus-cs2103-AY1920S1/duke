package command;

import exception.DukeException;
import storage.Storage;
import tasks.TaskList;
import ui.Ui;


public abstract class Command {
    /**
     * Tell whether the command is exit command.
     *
     * @return true when it is.
     */
    public abstract boolean isExit();

    /**
     * Execute the command according to the command type.
     *
     * @param taskList The list of tasks maintained in Duke
     * @param ui       Ui module
     * @param storage  in charge of loading and saving the tasks
     * @throws DukeException when the command is invalid
     */
    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException;
}
