package command;

import duke.Storage;
import duke.Ui;
import task.Task;
import task.TaskList;

/**
 * Represents a command that lists all the tasks in the task list.
 */
public class ListCommand extends Command {

    /**
     * Executes this task.
     *
     * @param tasks All the tasks that the user currently has.
     * @param ui The Ui object associated with Duke.
     * @param storage The Storage object associated with Duke
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showLine();
        ui.println("     Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            Task currentTask = tasks.getTasks().get(i);
            System.out.println("     " + Integer.toString(i + 1) + "." + currentTask.getTypeIcon()
                    + currentTask.getStatusIcon() + " " + currentTask);
        }
        ui.showLine();
    }
}
