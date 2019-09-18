package seedu.duke.commands;

import seedu.duke.exceptions.InvalidArgumentException;
import seedu.duke.storage.TaskList;
import seedu.duke.trackables.Deadline;
import seedu.duke.ui.Ui;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Abstraction of the Deadline Command.
 * Contains the {@code description} and {@code dateString} required to create a Deadline Object to add to the list.
 * eg: deadline someDeadline /by 20191212 2359
 */
public class DeadlineCommand extends Command {

    private String description;
    private String dateString;

    public DeadlineCommand(String description, String dateString) {
        this.description = description;
        this.dateString = dateString;
    }

    /**
     * Adds a Deadline with the description {@code description} and by {@code dateString} in its date form
     * to the TaskList {@code tasks}.
     * @param tasks The current TaskList instance.
     * @throws InvalidArgumentException thrown when there was an issue parsing the {@code dateString}.
     *         Likely causes are incorrect date formats.
     */
    @Override
    public void execute(TaskList tasks) throws InvalidArgumentException {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMd HHmm");
            dateFormat.setLenient(false);
            Date date = dateFormat.parse(dateString);
            Deadline deadline = new Deadline(description, date);
            tasks.add(deadline);
            Ui.printMessages("Got it. I've added this task:",
                "  " + deadline.toString(),
                "Now you have " + tasks.size() + " tasks in the list.");
        } catch (ParseException pe) {
            throw new InvalidArgumentException("Date input is not in the right format yyyyMMd HHmm", pe);
        }
    }
}

