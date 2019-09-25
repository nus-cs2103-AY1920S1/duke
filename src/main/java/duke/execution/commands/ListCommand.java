package duke.execution.commands;

import duke.exception.DukeException;
import duke.execution.Storage;
import duke.execution.TaskList;
import duke.execution.UI;

/**
 * Represents the characteristics of a ListCommand object.
 */
public class ListCommand extends Command {

    /**
     * Constructs a ListCommand object.
     *
     * @param text can be empty.
     */
    public ListCommand(String text) {
        super(text);
    }

    /**
     * Execution of a ListCommand object. This duke.execution is just to print out the list of tasks in the current
     * arraylist.
     *
     * @param taskList that we want to print.
     * @param ui to set a response from duke.
     * @param storage to store any changes in the storage.
     * @throws DukeException is thrown if there are any errors.
     */
    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws DukeException {

        super.execute(taskList, ui, storage);
        checkValidity();

        ui.listTasks(taskList);

    }

}
