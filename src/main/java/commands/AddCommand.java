package commands;

import exceptions.DukeException;
import storage.Storage;
import tasks.Task;
import tasks.TaskList;
import ui.Ui;

public class AddCommand extends Command {
    private Task task;

    public AddCommand(Task task) {
        super();
        this.task = task;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            taskList.addNewTask(task);
            storage.uploadTasksToFile(taskList.getTasks());
            return ui.showAddTaskMsg(taskList.getListSize(), task.toString());
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
