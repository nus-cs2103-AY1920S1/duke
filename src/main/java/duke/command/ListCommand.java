package duke.command;

import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Represents a Command to show the list of tasks.
 */
public class ListCommand extends Command {

    /**
     * Instantiates a list Command.
     */
    public ListCommand() {
        super(false);
    }

    /**
     * Calls the Ui object to show the list of tasks.
     *
     * @param taskList task list for the command to operate on
     * @param ui ui object to print messages according to the command
     * @param storage storage for the task list to be written
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showList(taskList);
    }
}
