package seedu.duke.task;

import seedu.duke.exception.DukeException;
import seedu.duke.exception.DukeWrongDateFormatException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;

/**
 * Class used to create and store Event tasks.
 * @author Lim Daekoon
 */
public class Event extends Task {

    private Date at;
    private SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy HHmm");

    /**
     * Constructs a new Event object with 'done' status set to false.
     * @param task Description of the task.
     * @param at Time this event occurs, in String form
     */
    public Event(String task, String at) throws DukeException {
        super(task);
        try {
            this.at = dateFormatter.parse(at);
        } catch (ParseException e) {
            throw new DukeWrongDateFormatException();
        }
    }

    /**
     * Constructs a new Event object with the 'done' status passed in as a parameter.
     * Used when loading data from saved file.
     * @param task Description of the task.
     * @param done Status of the task.
     * @param at Time this event occurs, in String form.
     */
    public Event(String task, String done, String at) throws DukeException {
        super(task);
        try {
            this.at = dateFormatter.parse(at);
        } catch (ParseException e) {
            throw new DukeWrongDateFormatException();
        }
        if (done.equals("1")) {
            super.markAsDone();
        }
    }

    /**
     * Converts this Event object into storage string form.
     * @return Data of this object, in storage string form.
     */
    public String toStorageString() {
        String output = "E|";
        if (super.isDone) {
            output = output + "1|";
        } else {
            output = output + "0|";
        }
        output = output + super.taskName + "|" + dateFormatter.format(this.at);
        return output;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + dateFormatter.format(at) + ")";
    }
}
