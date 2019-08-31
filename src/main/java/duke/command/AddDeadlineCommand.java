package duke.command;

import duke.exception.InvalidParameterException;
import duke.task.Deadline;

/**
 * The <code>AddDeadlineCommand</code> is created when the user enters <code>"deadline"</code>. The todo command add a \
 * deadline task into the list of tasks in {@link duke.task.TaskList}. The user interface will display the new deadline
 * task that is added.
 */
public class AddDeadlineCommand extends AddCommand {

    /**
     * Constructs a new add deadline command that will be executed in the <code>run</code> method of
     * {@link duke.main.Duke} with the specified line as a parameter.
     * @param line the line contents of the command passed as a parameter
     * @throws InvalidParameterException if the line is blank
     */
    public AddDeadlineCommand(String line) {
        super(line);
        try {
            String[] arr = super.line.split(" /by ");
            super.task = new Deadline(arr[0], arr[1]);
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
