package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.UI;
import duke.exception.TaskDoesNotExistException;

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
     * @param tasks   <code>TaskList</code> object which holds the taskList
     *                and various methods to operate on the taskList
     * @param ui      <code>UI</code> object which handles console output
     * @param storage <code>Storage</code> object which allows for reading
     *                result of executed command into preset task.txt file
     * @throws TaskDoesNotExistException if taskNumber does not exist in taskList
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws TaskDoesNotExistException {
        int taskNumber = Integer.parseInt(commandInformation);
        tasks.markTaskAsCompleted(taskNumber, true);
        storage.writeToTasksFile(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
