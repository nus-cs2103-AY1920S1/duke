package duke.command;

import duke.ui.MessageHandler;
import duke.utilities.Storage;
import duke.task.TaskList;
import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Task;

import java.text.ParseException;

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
        String[] deadlineParts = deadline.split(" /by ");
        String deadLineText = deadlineParts[0];
        String by = deadlineParts[1];
        String response;

        try {
            Task task = new Deadline(deadLineText, by);
            tasks.addTask(task);
            response = messageHandler.addTaskConfirmationMessage(task);
        } catch (ParseException error) {
            response = error.getMessage() + ". Please enter date in this format DD/MM/YYYY HHMM";
        }

        storage.writeToTasksFile(tasks);
        return response;
    }

}
