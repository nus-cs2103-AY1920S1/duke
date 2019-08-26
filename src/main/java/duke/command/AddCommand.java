package duke.command;

import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * A class representing a add command.
 */
public class AddCommand extends Command{
    private Task task;

    /**
     * Class constructor specifying the task.
     * @param task
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes the command.
     * Add the task to the list.
     * @param tasks a list task to work on.
     * @param ui an user interface to show messages.
     */
    @Override
    public void execute(TaskList tasks, Ui ui){
        tasks.addTask(task);
        ui.showAddedTask(task, tasks.getSize());
    }
}
