package execution.commands;


import exception.DukeException;
import execution.Storage;
import execution.TaskList;
import execution.UI;
import models.Task;

public class DoneCommand extends Command {
    public DoneCommand(String description) {
        super(description);
    }

    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws DukeException {
        super.execute(taskList, ui, storage);
        checkValidity();

        Task doneTask = taskList.getTaskByIndex(Integer.parseInt(this.descriptionOfTask.trim()) -1);
        doneTask.markAsDone();
        ui.displayDone(doneTask);
        storage.saveToDataFile(taskList);

    }

    @Override
    protected void checkValidity() throws DukeException {
        if (this.descriptionOfTask.isEmpty()) {
            throw new DukeException(" ☹ OOPS!!! The description of a done cannot be empty.");
        }
    }
}
