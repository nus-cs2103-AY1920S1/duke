package execution.commands;


import exception.DukeException;
import execution.Storage;
import execution.TaskList;
import execution.UI;
import models.Task;

/**
 * Represents the characteristics of a Done Command class.
 */
public class DoneCommand extends Command {

    /**
     * Constructs a done command with a input string, which is supposedly an integer.
     *
     * @param description an integer string.
     */
    public DoneCommand(String description) {

        super(description);

    }

    /**
     * Execution of a DoneCommand which would mark a task in the arraylist as done.
     *
     * @param taskList of current tasks
     * @param ui to set a response from duke.
     * @param storage to store any changes in the storage.
     * @throws DukeException
     */
    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws DukeException {

        super.execute(taskList, ui, storage);
        checkValidity();

        Task doneTask = taskList.getTaskByIndex(Integer.parseInt(this.descriptionOfTask.trim()) -1);
        doneTask.markAsDone();
        ui.displayDone(doneTask);
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
            throw new DukeException(" ☹ OOPS!!! The description of a done cannot be empty.");
        }

    }
}
