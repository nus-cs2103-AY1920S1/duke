package slave.command;

import slave.elements.TaskList;
import slave.elements.Ui;

/**
 * A command which represents exiting the Slave program
 */
public class ExitCommand extends Command {

    /**
     * Constructor for exit command
     */
    public ExitCommand(){
        this.commandType = CommandType.EXIT;
    }

    /**
     * executes by showing the goodbye message
     * @param taskList list containing current tasks
     * @param ui user interface
     */
    @Override
    public void execute(TaskList taskList, Ui ui){
        ui.showByeMessage();
    }
}

