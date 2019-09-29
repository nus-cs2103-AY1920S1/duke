package duke.command;

import duke.handler.Storage;
import duke.handler.Ui;
import duke.task.Task;
import duke.task.TaskList;

/**
 * ListCommand handles the listing of all tasks in Duke list of tasks.
 */
public class ListCommand extends Command {

    public ListCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.clearResponse();
        ui.showTasksMessage();
        for (int i = 1; i < tasks.getNumberOfTasks() + 1; i++) {
            Task currentTask = tasks.getTasks().get(i - 1);
            String line = String.format(i + "." + currentTask, currentTask.getStatusIcon());
            ui.setResponse(line);
            System.out.println(line);
        }
    }
    public boolean isExit() {
        return false;
    }
}
