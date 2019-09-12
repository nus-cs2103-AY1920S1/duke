package command;

import duke.Storage;
import duke.Ui;
import task.Priority;
import task.Task;
import task.TaskList;

/**
 * Represents a command that sets the priority level of a task.
 */
public class PriorityCommand extends Command {
    private int index;
    private Priority priority;

    public PriorityCommand(int index, Priority priority) {
        this.index = index;
        this.priority = priority;
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
        try {
            Task task = tasks.getTasks().get(index - 1);
            task.setPriority(priority);
            ui.println("The following task has been set to Priority " + priority.toString());
            ui.println(" " + Integer.toString(index) + ". " + task.toString());
        } catch (IndexOutOfBoundsException e) {
            ui.println("OOPS!!! Index out of bounds!");
        }
    }
}
