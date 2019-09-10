package duke.command;

import duke.ui.MessageHandler;
import duke.utilities.Storage;
import duke.task.TaskList;

/**
 * Inherits from abstract Command class.
 * Handles execution of commands in this format:
 * <code>bye</code>
 */
public class ExitCommand extends Command {

    public ExitCommand(String taskInformation) {
        super(taskInformation);
    }

    /**
     * Executes commands in this format:
     * <code>bye</code> and returns a goodbye message.
     *
     * @param tasks          <code>TaskList</code> object which holds the taskList
     *                       and various methods to operate on the taskList
     * @param messageHandler <code>UI</code> object which handles console output
     * @param storage        <code>Storage</code> object which allows for reading
     *                       result of executed command into preset task.txt file
     * @return <code>String</code> goodbye message
     */
    @Override
    public String execute(TaskList tasks, MessageHandler messageHandler, Storage storage) {
        return messageHandler.byeMessage();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
