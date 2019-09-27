package kappa.command;

import kappa.elements.TaskList;
import kappa.elements.Ui;

/**
 * Command which lists all the current tasks.
 */
public class ListCommand extends Command {

    /**
     * Constructor for list command.
     */
    public ListCommand() {
        this.commandType = CommandType.LIST;
    }

    /**
     * Executes by having the user interface print to the console all the tasks in the list.
     *
     * @param tasks List containing current tasks.
     * @param ui User interface.
     * @return String containing Kappa's formatted dialog.
     */
    @Override
    public String execute(TaskList tasks, Ui ui) {
        return ui.printListCommand(tasks);
    }
}
