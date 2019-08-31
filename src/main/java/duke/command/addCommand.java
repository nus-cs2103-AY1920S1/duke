package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.util.ArrayList;

public class addCommand implements Command{
    Task task;
    boolean isExit = false;
    public addCommand(Task task) {
        this.task = task;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.addTask(task);
        ArrayList<Task> list = tasks.getList();
        ui.showAdd(task, list.size());
        storage.saveFile(list);
    }
}
