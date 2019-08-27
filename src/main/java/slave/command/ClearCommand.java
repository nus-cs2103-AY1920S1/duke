package slave.command;

import slave.elements.TaskList;
import slave.elements.Ui;
import slave.exception.DukeException;

/**
 * Command that clears storage and tasklist
 */
public class ClearCommand extends Command {

    /**
     * Constructor
     */
    public ClearCommand(){
        this.commandType = CommandType.CLEAR;
    }

    /**
     * executes command by clearing list and storage and printing a clear message to the user
     * @param taskList list containing current tasks
     * @param ui user interface
     * @throws DukeException if list is not found
     */
    @Override
    public void execute(TaskList taskList, Ui ui) throws DukeException {
        taskList.clearList();
        ui.printClearCommand();
    }
}
