package slave.command;

import slave.elements.TaskList;
import slave.elements.Ui;

/**
 * Command which lists all the current tasks
 */
public class ListCommand extends Command {

    /**
     * Constructor for list command
     */
    public ListCommand() {
        this.commandType = CommandType.LIST;
    }

    /**
     * Executes by having the user interface print to the console all the tasks in the list.
     *
     * @param taskList List containing current tasks.
     * @param ui User interface.
     */
    @Override
    public void execute(TaskList taskList, Ui ui) {
        ui.printListCommand(taskList);
    }
}
