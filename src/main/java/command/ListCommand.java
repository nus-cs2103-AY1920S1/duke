package command;

import main.Storage;
import main.TaskList;
import main.Ui;

/**
 * Represents the command to display the current list.
 */
public class ListCommand extends Command {

    /**
     * Displays the current list.
     *
     * @param task    The working TaskList object.
     * @param ui      The working Ui object.
     * @param storage The working storage object.
     *
     * @return String representation of the acknowledgement message.
     */
    public String execute(TaskList task, Ui ui, Storage storage) {
        return ui.printList(task.getList());
    }
}