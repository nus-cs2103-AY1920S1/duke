package command;

import duke.Storage;
import duke.Ui;
import task.Task;
import task.TaskList;

/**
 * Represents a command that deletes a task from the task list.
 */
public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int index) {
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
        try {
            assert index <= tasks.size() : "index out of range!";

            Task taskRemoved = tasks.remove(index - 1);

            ui.showLine();
            ui.println("     Noted. I've removed this task: ");
            ui.println("       " + taskRemoved.getTypeIcon() + taskRemoved.getStatusIcon()
                    + " " + taskRemoved);
            ui.println("     Now you have " + tasks.size() + " tasks in the list.");
            ui.showLine();
        } catch (IndexOutOfBoundsException e) {
            ui.println("OOPS!!! The item that you want to delete does not exist.");
        }
    }
}
