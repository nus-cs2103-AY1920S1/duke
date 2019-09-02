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
     *
     * @return duke response after finding
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(taskToAdd);
        storage.updateList(tasks.getList());
        return ui.print("Got it. I've added this task: \n" + taskToAdd + "\nNow you have "
                + tasks.getList().size() + " tasks in the list.");
    }
}
