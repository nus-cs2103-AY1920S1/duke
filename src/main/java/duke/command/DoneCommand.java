package duke.command;

import duke.handler.Storage;
import duke.handler.Ui;
import duke.task.Task;
import duke.task.TaskList;

/**
 * DoneCommand handles the marking of tasks as done.
 */
public class DoneCommand extends Command {

    public DoneCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        int taskIndex = Integer.parseInt(fullCommand.replaceAll("\\D+","")) - 1;
        Task completedTask = tasks.getTasks().get(taskIndex);
        tasks.doneTask(completedTask);
        ui.clearResponse();
        ui.showTaskDone(completedTask);
    }

    public boolean isExit() {
        return false;
    }
}
