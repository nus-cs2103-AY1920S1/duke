package duke.command;

import duke.task.TaskList;
import duke.ui.Ui;
import duke.storage.Storage;

/**
 * Represents the <code>Command</code> to delete all tasks from the task list.
 *
 * @author Clarence Koh
 * @version 1.0
 * @since 29th August 2019
 */
public class DeleteAllCommand extends Command {

    public DeleteAllCommand() {
        super();
    }

    /**
     * This method allows for the execution of the deleteAll command which deletes all tasks from
     * <code>TaskList tasks</code>.
     *
     * @param tasks The task lists which contains all the user added tasks.
     * @param ui The user interface which deals with user input and interaction.
     * @param storage The storage to load and save task data into the output file.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.deleteAll();
        ui.printDeleteAllMessage();
    }
}