package command;

import task.Task;
import ui.UserInterface;
import duke.Storage;
import task.TaskList;

/**
 * Specifies the 'add' action to add user-specified tasks.
 */
public class AddCommand extends Command {
    private Task task;

    /**
     * Constructs the Add Command object with specified task.
     * @param task Specified task.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Returns the specified task.
     * @return Specified task.
     */
    public Task getTask() {
        return task;
    }

    /**
     * Executes the command and runs the related logic flow.
     * @param tasks List of tasks.
     * @param ui User interface.
     * @param storage Tasks storage: load & save.
     */
    public void execute(TaskList tasks, UserInterface ui, Storage storage) {
        //check for duplicate
        if (!tasks.isDuplicateTask(task)) {
            //add task into the task list
            tasks.add(task);

            //display successful message and task count
            super.message = "Got it. I've added this task:\n";
            super.message += task.toString() + "\n";
            super.message += "Now you have " + tasks.getSize() + " tasks in the list.";
        } else {
            super.message = "Task is duplicated, hence, not added to task list.";
        }
    }
}
