package duke.command;

import duke.handler.Storage;
import duke.handler.Ui;
import duke.task.Task;
import duke.task.TaskList;

/**
 * DeleteCommand handles the deletion of tasks from Duke list of tasks.
 */
public class DeleteCommand extends Command {

    public DeleteCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        int taskIndex = Integer.parseInt(fullCommand.replaceAll("\\D+","")) - 1;
        Task deletedTask = tasks.getTasks().get(taskIndex);
        tasks.deleteTask(taskIndex);
        ui.clearResponse();
        ui.showTaskDeleted(deletedTask, tasks.getNumberOfTasks());
    }

    public boolean isExit() {
        return false;
    }
}
