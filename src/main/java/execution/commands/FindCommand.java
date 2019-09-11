package execution.commands;

import exception.DukeException;
import execution.Storage;
import execution.TaskList;
import execution.UI;
import models.Task;

import java.util.ArrayList;

public class FindCommand extends Command {
    public FindCommand(String description) {
        super(description);
    }

    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws DukeException {
        super.execute(taskList, ui, storage);
        checkValidity();

        ArrayList<Task> newList = new ArrayList<>();
        for (int i = 0; i < taskList.getList().size(); i++) {
            if (taskList.getList().get(i).contains(this.descriptionOfTask)) {
                newList.add(taskList.getList().get(i));
            }
        }
        ui.listTasks(new TaskList(newList));
    }

    @Override
    protected void checkValidity() throws DukeException {
        if (this.descriptionOfTask.isEmpty()) {
            throw new DukeException(" ☹ OOPS!!! The description of an delete cannot be empty.");
        }
    }
}
