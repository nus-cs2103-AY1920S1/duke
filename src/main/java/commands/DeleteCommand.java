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
     * @param tasks   the tasklist object to be used in this command
     * @param ui      the ui object to be used in this command
     * @param storage the storage object to be used in this command
     * @return duke response after deletion
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Task t = null;
        try {
            tasks.deleteTask(taskNumToDelete);
            assert tasks.getList() != null;
            storage.updateList(tasks.getList());

        } catch (IndexOutOfBoundsException e) {
            return "Invalid task number :(";
        } catch (Exception e) {
            return "Invalid input";
        }
        return ("Noted. I've removed this task: \n" + t + "\nNow you have "
                + tasks.getList().size() + " tasks in the list.");

    }
}
