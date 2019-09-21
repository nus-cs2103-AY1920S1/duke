package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.util.ArrayList;
import java.util.Date;

public class EditCommand implements Command {
    int order;
    Date newTime;

    public EditCommand(int order, Date newTime) {
        this.order = order;
        this.newTime = newTime;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ArrayList<Task> taskList = tasks.getList();
        Task newTask = tasks.editTask(order, newTime);
        storage.saveFile(taskList);
        return ui.showEdit(newTask, taskList.size(), order);
    }
}
