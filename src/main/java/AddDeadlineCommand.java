import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents the Command for adding Tasks and its subclasses.
 * A subclass of Command.
 */
public class AddDeadlineCommand extends Command {

    final String timePattern = "d MMMM yyyy, h:mma";
    DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern(timePattern);

    /**
     * Overridden execute method from Command to add a Deadline object into the list of tasks.
     * It will throw an exception if the user inputs are unrecognisable for the
     * method to execute correctly.
     *
     * @param storage Storage object for saving purposes
     * @param tasks   Contains the list of tasks
     * @param ui      Holds Ui printing methods and user input field
     * @param input   User input
     * @return Add Task Message
     * @throws DukeException If inputs are invalid and format of subsequent fields is wrong
     */
    @Override
    public String execute(Storage storage, TaskList tasks, Ui ui, String input) throws DukeException {
        String[] userWords;
        if (input.isEmpty()) {
            throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
        }
        userWords = input.split("/by");
        if (userWords.length == 1) {
            throw new DukeException("OOPS!!! The date/time of a deadline cannot be empty or is wrongly typed.");
        }
        try {
            LocalDateTime dateObj = LocalDateTime.parse(userWords[1].trim(),
                    DateTimeFormatter.ofPattern("d/MM/yyyy Hmm"));
            String date = dateObj.format(dateTimeFormat);
            return tasks.addTask(new Deadline(userWords[0].trim(), date));
        } catch (DateTimeParseException e) {
            throw new DukeException("Could not recognise date and time. Please follow the format: dd/mm/yyyy HHmm.");
        }
    }
}
