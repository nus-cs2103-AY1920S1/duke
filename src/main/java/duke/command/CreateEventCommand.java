package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.UI;
import duke.exception.DukeException;
import duke.task.Event;

import java.text.ParseException;

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
     * Executes commands in this format:
     * <code>event taskName /at DD/MM/YYYY HHmm - DD/MM/YYYY HHmm</code>
     * Reads result of executed command into preset task.txt file
     *
     * @param tasks   <code>TaskList</code> object which holds the taskList
     *                and various methods to operate on the taskList
     * @param ui      <code>UI</code> object which handles console output
     * @param storage <code>Storage</code> object which allows for reading
     *                result of executed command into preset task.txt file
     * @throws DukeException if error related to Duke commands occurs
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws DukeException {
        String event = this.commandInformation;
        String[] eventParts = event.split(" /at ");
        String eventText = eventParts[0];
        String at = eventParts[1];

        try {
            tasks.addTask(new Event(eventText, at), true);
        } catch (ParseException error) {
            System.out.println("\t " + error.getMessage() + ". Please enter date in this format DD/MM/YYYY HHMM - DD/MM/YYYY HHMM");
        }

        storage.writeToTasksFile(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
