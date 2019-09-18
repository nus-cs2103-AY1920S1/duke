package duke.command;

import duke.ui.MessageHandler;
import duke.utilities.Storage;
import duke.task.TaskList;
import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Task;

import java.time.format.DateTimeParseException;

/**
 * Inherits from abstract Command class.
 * Handles execution of commands in this format:
 * <code>deadline taskName /by DD/MM/YYYY HHmm</code>
 */
public class CreateDeadlineCommand extends Command {
    public CreateDeadlineCommand(String commandInformation) {
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
     * @return a <code>String</code> referencing task execution status
     *         (success or error)
     * @throws DukeException if error related to Duke commands occurs
     */
    @Override
    public String execute(TaskList tasks, MessageHandler messageHandler, Storage storage) throws DukeException {
        String deadline = this.commandInformation;
        String[] deadlineParts = deadline.split(" /by | #");
        assert deadlineParts.length >= 2;

        String deadLineText = deadlineParts[0];
        String by = deadlineParts[1];
        String tag = "#";

        if (deadlineParts.length == 3) {
            tag += deadlineParts[2].replaceAll(" ", "");
        }

        String response;

        try {
            Task task = new Deadline(deadLineText, by, false, tag);
            tasks.addTask(task);
            response = messageHandler.addTaskConfirmationMessage(task);
        } catch (DateTimeParseException error) {
            response = error.getMessage() + ". Please enter date in this format DD/MM/YYYY HHMM";
            return response;
        }

        storage.writeToTasksFile(tasks);
        return response;
    }

}
