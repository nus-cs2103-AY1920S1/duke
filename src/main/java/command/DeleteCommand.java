package command;

import exception.DeleteException;
import storage.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;

/**
 * Command for deleting a task.
 */
public class DeleteCommand extends Command {

    protected String[] input;

    public DeleteCommand(String[] input) {
        this.input = input;
    }

    /**
     * Execute deleting a task in user's tasks list.
     * Output what is needed.
     * @param tasks the TaskList.
     * @param ui the User Interface which responsible for every output printing.
     * @param storage user's hard disk storage.
     * @return Executed output as String.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DeleteException {
        if (input.length <= 1) {
            throw new DeleteException();
        } else {
            int taskToDelete = Integer.parseInt(input[1]);
            if (tasks.getSize() < taskToDelete) { //check if it's a valid delete
                throw new DeleteException("OOPS!!! There is no such task in your list!"
                        + "Current number of tasks = " + tasks.getTaskList().size());
            } else {
                String output = "";
                Task task = tasks.deleteTask(taskToDelete - 1);
                output += "Noted I've removed this task:\n";
                output += "  " + task + "\n";
                if (tasks.getSize() > 1) {
                    output += "Now you have " + tasks.getSize() + " tasks in the list.\n";
                } else {
                    output += "Now you have " + tasks.getSize() + " task in the list.\n";
                }
                return output;
            }
        }
    }
}
