package command;

import task.Task;
import task.TaskList;
import ui.UserInterface;
import duke.Storage;

/**
 * Specifies the 'delete' action to delete according to task index.
 */
public class DeleteCommand extends Command {
    private int index;

    /**
     * Constructs the Delete Command object with specified task index.
     * @param index Task index.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the command and runs the related logic flow.
     * @param tasks List of tasks.
     * @param ui User interface.
     * @param storage Tasks storage: load & save.
     */
    public void execute(TaskList tasks, UserInterface ui, Storage storage) {
        Task task = tasks.remove(index);

        //display successful message and task count
        super.message = "Noted. I've removed this task:\n";
        super.message += task.toString() + "\n";
        super.message += "Now you have " + tasks.getSize() + " tasks in the list.";
    }
}