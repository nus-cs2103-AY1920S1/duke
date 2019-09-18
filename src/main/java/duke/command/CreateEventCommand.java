package duke.command;

import duke.ui.MessageHandler;
import duke.utilities.Storage;
import duke.task.TaskList;
import duke.exception.DukeException;
import duke.task.Event;
import duke.task.Task;

import java.text.ParseException;
import java.time.format.DateTimeParseException;

/**
 * Inherits from abstract Command class.
 * Handles execution of commands in this format:
 * <code>event taskName /at DD/MM/YYYY HHmm - DD/MM/YYYY HHmm</code>
 */
public class CreateEventCommand extends Command {
    public CreateEventCommand(String commandInformation) {
        super(commandInformation);
    }

    /**
     * Executes commands with this format:
     * <code>event taskName /at DD/MM/YYYY HHmm - DD/MM/YYYY HHmm</code>
     * Reads result of executed command into preset task.txt file
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
        String event = this.commandInformation;
        String[] eventParts = event.split(" /at | #");

        assert eventParts.length >= 2;

        String eventText = eventParts[0];
        String at = eventParts[1];

        String tag = "#";
        if (eventParts.length == 3) {
            tag += eventParts[2].replaceAll(" ", "");
        }

        String response;
        try {
            Task t = new Event(eventText, at, false, tag);
            tasks.addTask(t);
            response = messageHandler.addTaskConfirmationMessage(t);
        } catch (DateTimeParseException error) {
            response = error.getMessage() + ". Please enter date in this format DD/MM/YYYY HHMM - DD/MM/YYYY HHMM";
        }
        storage.writeToTasksFile(tasks);

        return response;
    }

}
