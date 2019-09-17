package duke.commands;


import duke.core.TaskList;
import duke.core.Ui;



/**
 * Represents a command which contains an execute method that lists the tasks in the task list.
 */
public class ListCommand extends Command {

    /**
     * Initialises the list command
     */
    public ListCommand(){
        this.commandType = CommandType.LIST;
    }

    /**
     * Lists all the tasks in the task list and prints them out.
     *
     * @param taskList The main task list of the application.
     * @param ui The main user interface of the application.
     */
    @Override
    public void execute(TaskList taskList, Ui ui) {
        ui.printNumberList(taskList);
    }
}
