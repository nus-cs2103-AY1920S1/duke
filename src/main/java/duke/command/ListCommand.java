package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Class representing a command to list items in a task list.
 */
public class ListCommand extends Command {
    /**
     * Executes this command on the given task list and user interface.
     *
     * @param tl The task list.
     * @param ui The user interface displaying events on the task list.
     * @param storage The place where tasks will be stored.
     */
    public void execute(TaskList tl, Ui ui, Storage storage) {
        ui.printMessage("Here are the tasks in your list:");
        for (int i = 1; i <= tl.size(); i++) {
            ui.printMessage(i + ". " + tl.get(i));
        }
    }
}
