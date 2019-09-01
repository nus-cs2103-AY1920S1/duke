package duke.command;

import duke.Ui;
import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tasks.TaskList;

/**
 * Represents the actions to execute when the command 'list' is
 * triggered.
 */

public class ListCommand extends Command {

    /**
     * Prints all the tasks in the list.
     *
     * @param tasks   List of Tasks
     * @param ui      User Interface displaying the tasks in the TaskList
     * @param storage External storage where the list of tasks is stored
     */

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTasks(tasks);
    }
}
