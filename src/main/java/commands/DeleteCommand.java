package commands;

import duke.TaskList;
import duke.Ui;
import duke.Storage;
import tasks.Task;

public class DeleteCommand extends Command {
    int taskNumToDelete;

    public DeleteCommand(int taskNumToDelete) {
        this.taskNumToDelete = taskNumToDelete;
    }

    /**
     * This method is used to delete one item from the task list
     * after user request.
     *
     * @return duke response after deletion
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Task t = tasks.deleteTask(taskNumToDelete);
            storage.updateList(tasks.getList());
            return ("Noted. I've removed this task: \n" + t + "\nNow you have "
                    + tasks.getList().size() + " tasks in the list.");
        } catch(IndexOutOfBoundsException e) {
            return "Invalid task number :(";
        } catch (Exception e) {
            return "Invalid input";
        }

    }
}
