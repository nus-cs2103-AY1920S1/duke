package duke.command;

import duke.ui.MessageHandler;
import duke.utilities.Storage;
import duke.task.TaskList;
import duke.exception.TaskDoesNotExistException;
import duke.task.Task;

/**
 * Inherits from abstract Command class.
 * Handles execution of commands in this format:
 * <code>done taskNumberToMarkAsDone</code>
 */
public class MarkAsDoneCommand extends Command {
    public MarkAsDoneCommand(String taskInformation) {
        super(taskInformation);
    }

    /**
     * Executes commands in this format:
     * <code>done taskName</code>
     * Reads result of executed command into preset task.txt file
     *
     * @param tasks          <code>TaskList</code> object which holds the taskList
     *                       and various methods to operate on the taskList
     * @param messageHandler <code>UI</code> object which handles console output
     * @param storage        <code>Storage</code> object which allows for reading
     *                       result of executed command into preset task.txt file
     * @return <code>String</code> representing the task that was successfully marked as completed
     * @throws TaskDoesNotExistException if taskNumber does not exist in taskList
     */
    @Override
    public String execute(TaskList tasks, MessageHandler messageHandler, Storage storage)
            throws TaskDoesNotExistException {
        int taskNumber = Integer.parseInt(commandInformation);
        Task t = tasks.markTaskAsCompleted(taskNumber);
        assert t.getCompletionStatus();
        storage.writeToTasksFile(tasks);

        return messageHandler.printMarkTaskAsCompletedMessage(t);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
