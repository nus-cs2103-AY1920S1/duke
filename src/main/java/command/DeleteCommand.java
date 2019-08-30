package command;

import task.Task;
<<<<<<< .merge_file_a00688
import duke.TaskList;
import duke.UserInterface;
import duke.Storage;

public class DeleteCommand extends Command {
    private int index;

=======
import task.TaskList;
import duke.UserInterface;
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
>>>>>>> .merge_file_a14060
    public DeleteCommand(int index) {
        this.index = index;
    }

<<<<<<< .merge_file_a00688
=======
    /**
     * Executes the command and runs the related logic flow.
     * @param tasks List of tasks.
     * @param ui User interface.
     * @param storage Tasks storage: load & save.
     */
>>>>>>> .merge_file_a14060
    public void execute(TaskList tasks, UserInterface ui, Storage storage) {
        Task task = tasks.remove(index);

        //display successful message and task count
        System.out.println("\tNoted. I've removed this task:");
        System.out.println("\t" + task.toString());
        System.out.println("\tNow you have " + tasks.getSize() + " tasks in the list.");
    }
}