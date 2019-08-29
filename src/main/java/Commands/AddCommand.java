package Commands;

import Exceptions.DukeException;
import Storage.Storage;
import Tasks.Task;
import Tasks.TaskList;
import Ui.Ui;

public class AddCommand extends Command {
    private Task task;

    public AddCommand(Task task) {
        super();
        this.task = task;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            taskList.addNewTask(task);
            ui.showAddTaskMsg(taskList.getListSize(), task.toString());
            storage.uploadTasksToFile(taskList.getTasks());
        } catch (DukeException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
