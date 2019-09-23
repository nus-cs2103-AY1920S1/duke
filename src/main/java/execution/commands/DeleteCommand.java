package execution.commands;

import exception.DukeException;
import execution.Storage;
import execution.TaskList;
import execution.UI;
import models.Task;

/**
 * Represents the characteristics of a delete Command.
 */
public class DeleteCommand extends Command {

    /**
     * Contructs a delete command with the input string, which is supposedly an integer.
     *
     * @param description an integer string.
     */
    public DeleteCommand(String description) {

        super(description);

    }

    /**
     * Execution of a DeleteCommand which would remove a task off the arraylist.
     *
     * @param taskList of current tasks.
     * @param ui to set a response from duke.
     * @param storage to store any changes in the storage.
     * @throws DukeException if the description is empty.
     */
    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws DukeException {

        super.execute(taskList, ui, storage);

        int deleteNum = Integer.parseInt(this.descriptionOfTask.trim()) - 1;
        Task deleted = taskList.deleteTask(deleteNum);
        ui.displayDeletedTask(deleted, taskList.getSize());
        storage.saveToDataFile(taskList);

    }

    /**
     * Handles the error and checks if it is valid for execution.
     *
     * @throws DukeException if description (the number) is left empty.
     */
    @Override
    protected void checkValidity() throws DukeException {
        if (this.descriptionOfTask.isEmpty()) {
            throw new DukeException(" ☹ OOPS!!! The description of an delete cannot be empty.");
        }
    }
}
