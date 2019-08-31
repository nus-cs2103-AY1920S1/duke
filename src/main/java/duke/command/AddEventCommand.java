package duke.command;

import duke.exception.InvalidParameterException;
import duke.task.Event;

/**
 * The <code>AddEventCommand</code> is created when the user enters <code>"event"</code>. The todo command add a event
 * task into the list of tasks in {@link duke.task.TaskList}. The user interface will display the new event task that
 * is added.
 */
public class AddEventCommand extends AddCommand {

    /**
     * Constructs a new add event command that will be executed in the <code>run</code> method of {@link duke.main.Duke}
     * with the specified line as a parameter.
     * @param line the line contents of the command passed as a parameter
     * @throws InvalidParameterException if the line is blank
     */
    public AddEventCommand(String line) {
        super(line);
        try {
            String[] arr = super.line.split(" /at ");
            super.task = new Event(arr[0], arr[1]);
        } catch(ArrayIndexOutOfBoundsException aioobe) {
            throw new InvalidParameterException(line.isBlank() ? null : line);
        }
    }

    /**
     * Returns <code>true</code> if the command is an exit command and <code>false</code> otherwise.
     * @return <code>false</code>
     */
    public boolean isExit() {
        return false;
    }

}
