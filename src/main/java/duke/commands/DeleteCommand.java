package duke.commands;

import duke.DukeException;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.Storage;
import duke.Ui;

public class DeleteCommand extends Command {
    private int taskIndex;

    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Task task = taskList.getTask(taskIndex);
        taskList.removeTask(taskIndex);
        ui.showDelete(task, taskList);
        storage.saveData(taskList);
    }
}
