package duke.command;

import duke.task.Task;
import duke.TaskList;
import duke.Ui;

public abstract class AddCommand extends Command {
    protected final String description;

    public AddCommand(String description) {
        this.description = description;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Adds a given task to the task list, showing the appropriate messages through UI.
     * @param tasks Task list
     * @param ui UI for output
     * @param task Task to be added to the task list
     */
    public void executeAddTask(TaskList tasks, Ui ui, Task task) {
        tasks.addTask(task);
        ui.showTaskAdded(task);
        ui.showNumTasks(tasks.getNumTasks());
    }
}
