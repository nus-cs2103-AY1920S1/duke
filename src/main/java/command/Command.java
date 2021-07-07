package command;

import task.TaskList;
import util.Storage;

public abstract class Command {

    protected String inputCommand;

    protected Command() {
    }

    /**
     * Abstract method to execute inputCommand.
     *
     * @param taskList the list of tasks
     * @param storage  storage for saving and loading from file
     **/
    public abstract String executeCommand(TaskList taskList, Storage storage);
}
