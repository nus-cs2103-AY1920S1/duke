package duke.command;

import duke.handler.Storage;
import duke.handler.Ui;
import duke.task.Task;
import duke.task.TaskList;

/**
 * AddCommand handles the adding of tasks into Duke list of tasks.
 */
public class AddCommand extends Command {

    protected Task addedTask;

    public AddCommand(String fullCommand, Task addedTask) {
        this.fullCommand = fullCommand;
        this.addedTask = addedTask;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(addedTask);
        ui.clearResponse();
        ui.showTaskAdded(addedTask, tasks.getNumberOfTasks());
    }

    public boolean isExit() {
        return false;
    }
}
