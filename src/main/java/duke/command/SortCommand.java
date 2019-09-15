package duke.command;

import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Represents the Command to sort the task list by description.
 */
public class SortCommand extends Command {

    /**
     * Instantiates a Sort Command.
     */
    public SortCommand() {
        super(false);
    }

    /**
     * Sorts the list of tasks by description.
     *
     * @param taskList task list for the command to operate on
     * @param ui ui object to print messages according to the command
     * @param storage storage for the task list to be written
     * @return response from ui
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return taskList.sortByDescription(ui);
    }
}
