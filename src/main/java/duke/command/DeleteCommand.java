package duke.command;

import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.storage.Storage;

/**
 * Represents a delete command.
 * Each instance is a command to delete a task from the task list.
 */
public class DeleteCommand extends Command {
    /** Index of the task to be deleted. */
    private int index;

    /**
     * Creates an instance of delete command.
     * Sets isExit to false as it is not an exit command.
     *
     * @param index Index of task to be deleted.
     */
    public DeleteCommand(int index) {
        this.index = index;
        isExit = false;
    }

    /**
     * Deletes the task from the task list, which is determined by a 1-based index.
     * Prints the response.
     * Writes the changes to the data file.
     *
     * @param taskList TaskList of tasks.
     * @param ui Ui object for user interaction.
     * @param storage Storage object for data file.
     * @return Returns a string of the response from duke after executing this command.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        Task task = taskList.deleteTask(index);
        Task.minusFromTotalTasks();
        String indent = ui.getIndent();
        String message;
        if (task == null) {
            message = indent + "Index out of range, no task found.";
        } else {
            message = indent + "Noted. I've removed this task:\n"
                    + indent + "  " + task + "\n"
                    + indent + "Now you have " + taskList.getTotalTasks() + " tasks in the list.";
        }
        storage.write(taskList);
        return message;
    }
}
