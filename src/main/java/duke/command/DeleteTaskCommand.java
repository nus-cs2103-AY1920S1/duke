package duke.command;

import duke.ui.MessageHandler;
import duke.utilities.Storage;
import duke.task.TaskList;
import duke.exception.TaskDoesNotExistException;
import duke.task.Task;

/**
 * Inherits from abstract Command class.
 * Handles execution of commands in this format:
 * <code>delete taskNumberToDelete</code>
 */
public class DeleteTaskCommand extends Command {
    public DeleteTaskCommand(String commandInformation) {
        super(commandInformation);
    }

    /**
     * Executes commands in this format:
     * <code>delete taskName</code>
     * Reads result of executed command into preset task.txt file
     *
     * @param tasks          <code>TaskList</code> object which holds the taskList
     *                       and various methods to operate on the taskList
     * @param messageHandler <code>UI</code> object which handles console output
     * @param storage        <code>Storage</code> object which allows for reading
     *                       result of executed command into preset task.txt file
     * @return a <code>String</code> referencing task execution status
     *         (successful or failed)
     * @throws TaskDoesNotExistException if taskNumber does not exist in taskList
     */
    @Override
    public String execute(TaskList tasks, MessageHandler messageHandler, Storage storage)
            throws TaskDoesNotExistException {
        int taskNumber = Integer.parseInt(commandInformation);
        Task t = tasks.deleteTask(taskNumber);
        String response = messageHandler.taskDeletedConfirmationMessage(t);
        storage.writeToTasksFile(tasks);

        return response;
    }

}
