package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents the <code>Command</code> to list from the task list in
 * the <code>Duke</code> application.
 *
 * @author Clarence Koh
 * @version 1.0
 * @since 29th August 2019
 */
public class ListCommand extends Command {

    /**
     * This method when called results in the listing of all the task from the task list in the
     * <code>Duke</code> application.
     *
     * @param tasks The task lists which contains all the user added tasks.
     * @param ui The user interface which deals with user input and interaction.
     * @param storage The storage to load and save task data into the output file.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            ui.printTaskList(tasks.getAllTasks());
        } catch (AssertionError | DukeException e) {
            ui.printError(e.getMessage());
        }
    }
}