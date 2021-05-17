package command;

import duke.Storage;
import duke.Ui;
import task.Task;
import task.TaskList;

/**
 * Represents a command that marks as done a task in the task list.
 */
public class DoneCommand extends Command {
    private int index;

    public DoneCommand(int index) {
        this.index = index;
    }

    /**
     * Executes this task.
     *
     * @param tasks All the tasks that the user currently has.
     * @param ui The Ui object associated with Duke.
     * @param storage The Storage object associated with Duke
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        assert index <= tasks.size() : "index out of range!";

        Task taskDone = tasks.getTasks().get(index - 1);
        taskDone.markAsDone();

        ui.showLine();
        ui.println("     Nice! I've marked this task as done: ");
        ui.println("       " + taskDone.getTypeIcon() + taskDone.getStatusIcon() + " " + taskDone);
        ui.showLine();
    }
}
