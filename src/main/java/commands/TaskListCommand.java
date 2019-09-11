package commands;

import logic.DukeList;
import logic.Storage;
import logic.Ui;

/**
 * Encapsulates command to get and print an ordered list of tasks.
 */
public class TaskListCommand extends TaskCommands {

    /**
     * Overridden Method to execute the GetList command.
     *
     * @param list   list of tasks
     * @param ui      User Interface
     * @param storage File Storage and Management
     */
    @Override
    public void execute(DukeList list, Ui ui, Storage storage) {
        ui.printList(list.getList(), "printTask");
    }
}
