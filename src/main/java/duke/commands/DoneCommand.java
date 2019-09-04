package duke.commands;

import duke.DukeException;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.Storage;
import duke.Ui;

public class DoneCommand extends Command {
    private int taskIndex;

    public DoneCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Task task = taskList.getTask(this.taskIndex);
        task.markAsDone();
        ui.showDone(task);
        storage.saveData(taskList);
    }
}
