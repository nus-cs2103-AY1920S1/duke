package command;

import task.Task;
import task.TaskList;
import ui.UserInterface;
import duke.Storage;

/**
 * Specifies the 'done' action to mark task as 'done' according to task index.
 */
public class DoneCommand extends Command {
    private int index;

    /**
     * Constructs the Done Command object with specified task index.
     * @param index Task index.
     */
    public DoneCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the command and runs the related logic flow.
     * @param tasks List of tasks.
     * @param ui User interface.
     * @param storage Tasks storage: load & save.
     */
    public void execute(TaskList tasks, UserInterface ui, Storage storage) {
        Task task = tasks.done(index - 1);

        //display successful message
        super.message = "Nice! I've marked this task as done:\n";
        super.message += task.toString();
    }
}