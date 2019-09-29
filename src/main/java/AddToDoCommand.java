import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents the Command for adding Tasks and its subclasses.
 * A subclass of Command.
 */
public class AddToDoCommand extends Command {

    /**
     * Overridden execute method from Command to add a To Do object into the list of tasks.
     * It will throw an exception if the user inputs are unrecognisable for the
     * method to execute correctly.
     *
     * @param storage Storage object for saving purposes
     * @param tasks   Contains the list of tasks
     * @param ui      Holds Ui printing methods and user input field
     * @param input   User input
     * @return Add Task Message
     * @throws DukeException If input is empty
     */
    @Override
    public String execute(Storage storage, TaskList tasks, Ui ui, String input) throws DukeException {
        if (input.isEmpty()) {
            throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
        }
        return tasks.addTask(new Task(input));
    }
}
