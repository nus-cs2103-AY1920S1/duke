//Solution below adapted from https://nus-cs2103-ay1920s1.github.io/website/schedule/week2/project.html
package seedu.duke.tasks;

import seedu.duke.exceptions.DukeException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Task that includes a description and a date and time for the event.
 */
public class Event extends Task {

    protected Date at;
    protected String dateInString;
    protected static final String dateFormat = "dd/MM/yyyy HHmm";
    protected SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);

    /**
     * Constructor that specifies the description and the date of the event.
     *
     * @param description Description of the task.
     * @param dateInString Date and time of the event in the same format as "dd/mm/yyyy hhmm".
     * @throws DukeException Throws if the date and time are not in the format described above.
     */
    public Event(String description, String dateInString) throws DukeException {
        super(description);
        this.dateInString = dateInString;
        try {
            at = formatter.parse(dateInString);
        } catch (ParseException ex) {
            throw new DukeException("Not a valid time format!!");
        }
    }

    @Override
    public String toString() {
        return "[E]" + " " + super.toString() + " (at: " + dateInString + " )";
    }
}