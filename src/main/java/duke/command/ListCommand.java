package duke.command;

import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Class representing a duke.command to list items in a duke.task list.
 */
public class ListCommand extends Command {
    /**
     * Executes this duke.command on the given duke.task list and user interface.
     *
     * @param tl The duke.task list.
     * @param ui The user interface displaying events on the duke.task list.
     */
    public void execute(TaskList tl, Ui ui) {
        ui.printMessage("Here are the tasks in your list:");
        for (int i = 1; i <= tl.size(); i++) {
            ui.printMessage(i + ". " + tl.get(i));
        }
    }
}
