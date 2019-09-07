package duke.command;

import duke.ui.MessageHandler;
import duke.utilities.Storage;
import duke.task.TaskList;

/**
 * Inherits from abstract Command class.
 * Handles execution of commands in this format:
 * <code>list</code>
 */
public class ListCommand extends Command {
    public ListCommand(String taskInformation) {
        super(taskInformation);
    }

    /**
     * Prints taskList
     *
     * @param tasks   <code>TaskList</code> object which holds the taskList
     *                and various methods to operate on the taskList
     * @param messageHandler      <code>UI</code> object which handles console output
     * @param storage <code>Storage</code> object which allows for reading
     *                result of executed command into preset task.txt file
     */
    @Override
    public String execute(TaskList tasks, MessageHandler messageHandler, Storage storage) {
        return messageHandler.getAllTasksAsString();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
