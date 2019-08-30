package command;

import task.Task;
<<<<<<< .merge_file_a09900
import duke.TaskList;
import duke.UserInterface;
import duke.Storage;

public class AddCommand extends Command {
    private Task task;

=======
import task.TaskList;
import duke.UserInterface;
import duke.Storage;

/**
 * Specifies the 'add' action to add user-specified tasks.
 */
public class AddCommand extends Command {
    private Task task;

    /**
     * Constructs the Add Command object with specified task.
     * @param task Specified task.
     */
>>>>>>> .merge_file_a16052
    public AddCommand(Task task) {
        this.task = task;
    }

<<<<<<< .merge_file_a09900
=======
    /**
     * @return Specified task.
     */
>>>>>>> .merge_file_a16052
    public Task getTask() {
        return task;
    }

<<<<<<< .merge_file_a09900
=======
    /**
     * Executes the command and runs the related logic flow.
     * @param tasks List of tasks.
     * @param ui User interface.
     * @param storage Tasks storage: load & save.
     */
>>>>>>> .merge_file_a16052
    public void execute(TaskList tasks, UserInterface ui, Storage storage) {
        //add task into the task list
        tasks.add(task);

        //display successful message and task count
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t " + task.toString());
        System.out.println("\tNow you have " + tasks.getSize() + " tasks in the list.");
    }
}
