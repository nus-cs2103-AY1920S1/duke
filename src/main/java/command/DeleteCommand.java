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
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DeleteException {
        if (input.length <= 1) {
            throw new DeleteException();
        } else {
            int taskToDelete = Integer.parseInt(input[1]);
            if (tasks.getTaskList().size() < taskToDelete) { //check if it's a valid delete
                throw new DeleteException("OOPS!!! There is no such task in your list!"
                        + "Current number of tasks = " + tasks.getTaskList().size());
            } else {
                Task task = tasks.getTaskList().get(taskToDelete - 1);
                tasks.getTaskList().remove(taskToDelete - 1);
                ui.println("Noted I've removed this task:");
                ui.println("  " + task);
                if (tasks.getTaskList().size() > 1) {
                    ui.println("Now you have " + tasks.getTaskList().size() + " tasks in the list.");
                } else {
                    ui.println("Now you have " + tasks.getTaskList().size() + " task in the list.");
                }
            }
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
