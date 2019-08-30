package command;

import task.Task;
<<<<<<< .merge_file_a04164
import duke.TaskList;
import duke.UserInterface;
import duke.Storage;

public class DoneCommand extends Command {
    private int index;

=======
import task.TaskList;
import duke.UserInterface;
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
>>>>>>> .merge_file_a07408
    public DoneCommand(int index) {
        this.index = index;
    }

<<<<<<< .merge_file_a04164
=======
    /**
     * Executes the command and runs the related logic flow.
     * @param tasks List of tasks.
     * @param ui User interface.
     * @param storage Tasks storage: load & save.
     */
>>>>>>> .merge_file_a07408
    public void execute(TaskList tasks, UserInterface ui, Storage storage) {
        Task task = tasks.done(index);

        //display successful message
        System.out.println("\tNice! I've marked this task as done:");
        System.out.println("\t  " + task.toString());
    }
}