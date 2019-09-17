package duke.command;

import duke.exception.InvalidDateTimeException;
import duke.exception.InvalidParameterException;
import duke.task.Event;
import duke.task.TaskManager;

/**
 * The <code>AddEventCommand</code> is created when the user enters <code>"event"</code>. The todo command add a event
 * task into the list of tasks in {@link TaskManager}. The user interface will display the new event task that
 * is added.
 */
public class AddEventCommand extends AddCommand {

    /**
     * Constructs a new add event command that will be executed in the <code>run</code> method of {@link duke.main.Duke}
     * with the specified line as a parameter.
     * @param line the line contents of the command passed as a parameter
     * @throws InvalidParameterException if the line is blank
     */
    public AddEventCommand(String line) throws InvalidParameterException {
        super(line);
        if (line.isBlank()) {
            throw new InvalidParameterException();
        }
        try {
            String[] arr = super.line.split(" /at ");
            assert(arr.length >= 2);
            super.task = new Event(getTaskDescription(arr), getTaskDate(arr));
        } catch (ArrayIndexOutOfBoundsException aioobe) {
            throw new InvalidParameterException(line);
        } catch (InvalidDateTimeException idte) {
            throw new InvalidParameterException(idte.getInvalidDateTime());
        }
    }

}
