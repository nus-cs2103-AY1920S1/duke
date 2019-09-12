package duke.task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Represents a deadline task.
 */

public class Deadline extends Task {
    protected String by;
    protected Date date;

    /**
     * Constructs a deadline class.
     * @param description description of the deadline task.
     * @param by date of deadline.
     * @throws ParseException if date is in wrong format.
     */
    public Deadline(String description, String by) throws ParseException {
        super(description);
        this.by = by;
        this.date = new SimpleDateFormat("dd/MM/yyyy hh:mm").parse(by);
    }

    /**
     * Calculates the number of days to the deadline.
     *
     * @return the number of days between current date versus deadline date.
     */

    public int getDiffDays() {
        Calendar cal = Calendar.getInstance();
        Date current = cal.getTime();
        long diff = date.getTime() - current.getTime();
        int diffDays = (int) (diff / (24 * 60 * 60 * 1000));
        return diffDays;
    }

    /**
     * Deals with changing the task to file format string.
     *
     * @return task as string.
     */
    public String format() {
        return "D" + super.format() + "|" + by;
    }

    /**
     * Deals with changing the task to print format string.
     *
     * @return task as string.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
