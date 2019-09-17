import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents the Command for adding Tasks and its subclasses.
 * A subclass of Command.
 */
public class AddEventCommand extends Command {

    /**
     * Overridden execute method from Command to add an Event object into the list of tasks.
     * It will throw an exception if the user inputs are unrecognisable for the
     * method to execute correctly.
     *
     * @param storage Storage object for saving purposes
     * @param tasks   Contains the list of tasks
     * @param ui      Holds Ui printing methods and user input field
     * @param input   User input
     * @return Add Task Message
     * @throws DukeException If inputs are empty and format of subsequent fields is wrong
     */
    @Override
    public String execute(Storage storage, TaskList tasks, Ui ui, String input) throws DukeException {

        String[] userWords;
        if (input.isEmpty()) {
            throw new DukeException("OOPS!!! The description of an event cannot be empty.");
        }
        userWords = input.split("/at");
        if (userWords.length == 1) {
            throw new DukeException("OOPS!!! The venue of an event cannot be empty or is wrongly typed.");
        }
        return tasks.addTask(new Event(userWords[0].trim(), userWords[1].trim()));

    }
}
