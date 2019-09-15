package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.util.ArrayList;

public class EditCommand implements Command {
    int order;
    Task task;

    public EditCommand(int order, Task task) {
        this.order = order;
        this.task = task;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ArrayList<Task> taskList = tasks.getList();
        tasks.editTask(order, task);
        storage.saveFile(taskList);
        return ui.showEdit(task, taskList.size(), order);
    }
}
