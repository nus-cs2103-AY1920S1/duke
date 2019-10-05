package duke.command;

import duke.exception.TaskDoesNotExistException;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.MessageHandler;
import duke.utilities.Storage;

/**
 * Inherits from abstract Command class.
 * Handles execution of commands in this format:
 * <code>taskNumber #hashTag</code>
 */
public class AddTagCommand extends Command {
    public AddTagCommand(String commandInformation) {
        super(commandInformation);
    }

    /**
     * Executes commands with this format: <code>deadline taskName /by DD/MM/YYYY HHmm</code>
     * and reads result of executed command into preset task.txt file
     *
     * @param tasks          <code>TaskList</code> object which holds the taskList
     *                       and various methods to operate on the taskList
     * @param messageHandler <code>UI</code> object which handles console output
     * @param storage        <code>Storage</code> object which allows for reading
     *                       result of executed command into preset task.txt file
     * @return a <code>String</code> referencing tag success
     * @throws TaskDoesNotExistException if task referenced by taskNumber does not exist
     */
    @Override
    public String execute(TaskList tasks, MessageHandler messageHandler, Storage storage)
            throws TaskDoesNotExistException {
        String[] parts = commandInformation.split(" #");
        int taskNumber = Integer.parseInt(parts[0]);

        // remove spaces in tag
        String tag = parts[1].replaceAll(" ", "");

        Task t = tasks.tagTask(taskNumber, tag);
        String response = messageHandler.tagAddedConfirmationMessage(t);
        storage.writeToTasksFile(tasks);

        return response;
    }

}
