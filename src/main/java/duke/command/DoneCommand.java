package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents the <code>Command</code> to mark a task as done from the task list.
 *
 * @author Clarence Koh
 * @version 1.0
 * @since 29th August 2019
 */
public class DoneCommand extends Command {

    /**
     * Represents the zero-based index of the task to be marked as done from the task list.
     */
    protected int doneTask;

    /**
     * Class constructor.
     *
     * @param doneTask The zero-based index of the task to be marked as done from the task list.
     */
    public DoneCommand(int doneTask) {
        super();
        this.doneTask = doneTask;
    }

    /**
     * This method allows for the execution of this command which marks the stated task from the
     * <code>TaskList tasks</code> as done.
     *
     * @param tasks The task lists which contains all the user added tasks.
     * @param ui The user interface which deals with user input and interaction.
     * @param storage The storage to load and save task data into the output file.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.markAsDone(this.doneTask);
        ui.printDone(tasks.get(this.doneTask).toString());
    }
}