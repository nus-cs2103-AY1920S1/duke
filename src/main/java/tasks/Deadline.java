package tasks;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;

/**
 * Encapsulates a deadline-type task handled by Duke.
 *
 * <p> A deadline differs from other tasks because it has a due date. </p>
 *
 * @author atharvjoshi
 * @version CS2103 AY19/20 Sem 1 iP Week 4
 */
public class Deadline extends Task {
    /** The date by which task is due. */
    private String dueDate;

    /** Fixed string pattern for date time format. */
    private static final String DATE_TIME_FORMAT_PATTERN = "dd/MM/yyyy HHmm";

    /** Tag identifying deadline. */
    private static final String DEADLINE_TAG = "D-";

    /** Date / Time converted to Date format. */
    private Date byWhen;

    /**
     * Creates a deadline with a description and due date.
     *
     * @param description string representing the description of the deadline
     * @param dueDate string representing date by which deadline is due
     * @param isDone flag indicating whether task has been done or not
     * @throws ParseException if the date/time is entered in the wrong format
     */
    public Deadline(String description, String dueDate, boolean isDone)
            throws ParseException {
        super(description, isDone);
        this.dueDate = dueDate;
        this.byWhen = this.toDate();
    }

    /**
     * Return a string representation of this deadline.
     *
     * @return string representing this deadline.
     */
    public String toString() {
        return DEADLINE_TAG + super.toString() + "-" + dueDate;
    }

    /**
     * Converts string representation of date/time into an actual date/time
     * object.
     *
     * @return the parsed date and time information
     * @throws ParseException if the date/time is entered in the wrong format
     */
    private Date toDate() throws ParseException {
        return new SimpleDateFormat(DATE_TIME_FORMAT_PATTERN).parse(this.dueDate);
    }
}
