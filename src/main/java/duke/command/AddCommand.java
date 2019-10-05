package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents the <code>Command</code> to add new tasks into the task list.
 *
 * @author Clarence Koh
 * @version 1.0
 * @since 29th August 2019
 */
public class AddCommand extends Command {

    /**
     * Represents the task to be added.
     */
    protected Task task;

    /**
     * Class constructor. Enables the addition of the <code>task</code> to the task list.
     *
     * @param task The task to be added into the task list.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * This method allows for the execution of the add command which adds the stated task
     * into the <code>TaskList tasks</code>.
     *
     * @param tasks The task lists which contains all the user added tasks.
     * @param ui The user interface which deals with user input and interaction.
     * @param storage The storage to load and save task data into the output file.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(task);
        ui.printAddTaskMessage(task.toString(), tasks.size());
    }
}