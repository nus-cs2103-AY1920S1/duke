package duke.task;

import duke.exception.DukeException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

/**
 * The Deadline class extends Task. It is a type of task that also has a deadline.
 */
public class Deadline extends Task {
    /**
     * The deadline for the task to be completed.
     */
    protected String by;
    private Date date;

    /**
     * Create an instance of Deadline. Date and time provided in the right format will be parsed into a
     * datetime object. Otherwise, the deadline will remain seen as the original string.
     * @param description Description of the deadline.
     * @param dateTime Date and Time at which the deadline is.
     */
    public Deadline(String description, String dateTime) throws DukeException {
        super(description);
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            this.date = dateFormat.parse(dateTime);
            this.by = date.toString();
        } catch (ParseException pe) {
            throw new DukeException("Please specify the deadline in this format: dd/mm/yyy hh:mm\n"
                    + "example: 01/12/2019 14:30");
        }
    }

    public static Comparator<Deadline> deadlineComparator = new Comparator<Deadline>() {
        @Override
        public int compare(Deadline d1, Deadline d2) {
            return (d1.date.before(d2.date) ? -1 :
                    (d1.date.after(d2.date) ? 1 : 0));
        }
    };

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
