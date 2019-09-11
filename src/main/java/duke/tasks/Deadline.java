package duke.tasks;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Deadline extends Task {

    private final Date date;

    /**
     * Initialises a Deadline task.
     *
     * @param description The deadline description.
     * @param date          The time at which the deadline is up.
     */
    public Deadline(String description, Date date) {
        super(description);
        this.date = (Date) date.clone();
    }

    /**
     * Gets the date of the deadline.
     *
     * @return The date of the deadline.
     */
    @Override
    public Date getDate() {
        return (Date) date.clone();
    }

    /**
     * The string representation of a deadline for printing.
     *
     * @return The deadline string for printing.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + "\n(by: "
                + new SimpleDateFormat("EEE, d MMM yyyy, hh:mm a").format(date) + ")";
    }

    /**
     * The string representation of a deadline for saving to file.
     *
     * @return The deadline string for storage.
     */
    @Override
    public String toStore() {
        return "D" + super.toStore() + "|" + new SimpleDateFormat("EEE, d MMM yyyy, hh:mm a").format(date);
    }
}