package duke.command;

import duke.TaskList;
import duke.ui.Ui;
import duke.task.Task;

public class AddCommand extends Command {
    private Task task;

    /**
     * Create add commands.
     * @param task task to be added
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Adds task to list of tasks, gives feedback to user after execution.
     *
     * @param tasks contains the current list of tasks
     * @param ui to give feedback to the user
     */
    public String execute(TaskList tasks, Ui ui) {
        tasks.add(this.task);
        return ui.showAddTaskMessage(tasks.size(), task.toString());
    }
}
