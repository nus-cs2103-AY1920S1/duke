package commands;

import duke.TaskList;
import duke.Ui;
import duke.Storage;
import tasks.Task;

public class AddCommand extends Command {
    Task taskToAdd;

    public AddCommand(Task taskToAdd) {
        this.taskToAdd = taskToAdd;
    }

    /**
     * This method is used to add a task to the list.
     * @param tasks the tasklist object to be used in this command
     * @param ui the tasklist object to be used in this command
     * @param storage the storage object to be used in this command
     * @return dukes response after finding
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(taskToAdd);
        assert taskToAdd != null;
        assert tasks.getList() != null;
        storage.updateList(tasks.getList());
        return ui.print("Got it. I've added this task: \n" + taskToAdd + "\nNow you have "
                + tasks.getList().size() + " tasks in the list.");
    }
}
