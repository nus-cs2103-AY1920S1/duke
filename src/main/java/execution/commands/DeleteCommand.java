package execution.commands;

import exception.DukeException;
import execution.Storage;
import execution.TaskList;
import execution.UI;
import models.Task;

public class DeleteCommand extends Command {
    public DeleteCommand(String description) {
        super(description);
    }

    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws DukeException {
        super.execute(taskList, ui, storage);

        int deleteNum = Integer.parseInt(this.descriptionOfTask.trim()) - 1;
        Task deleted = taskList.deleteTask(deleteNum);
        ui.displayDeletedTask(deleted, taskList.getSize());
        storage.saveToDataFile(taskList);

    }

    @Override
    protected void checkValidity() throws DukeException {
        if (this.descriptionOfTask.isEmpty()) {
            throw new DukeException(" ☹ OOPS!!! The description of an delete cannot be empty.");
        }
    }
}
