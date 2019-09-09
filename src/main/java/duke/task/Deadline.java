package duke.task;

import java.util.Date;
import java.text.SimpleDateFormat;

/**
 * Represents a task with a deadline to be met.
 */
public class Deadline extends Task implements Comparable<Deadline> {
    /**
     * Represents the date by which the tasks is to be completed by.
     */
    protected Date by;

    /**
     * Class constructor.
     *
     * @param description The description of the task.
     * @param by The date by which the task is to be completed.
     */
    public Deadline(String description, Date by) {
        super(description);
        this.by = by;
    }

    private Date getBy() {
        return this.by;
    }

    @Override
    public String toString() {
        SimpleDateFormat properFormat = new SimpleDateFormat("dd 'of' MMMM yyyy, hh:mm a");
        return "[D]" + "[" + super.getStatusIcon() + "] " + super.toString() + " (by: " + properFormat.format(this.by)
                + ")";
    }

    @Override
    public int compareTo(Deadline o) {
        return (this.by).compareTo(o.getBy());
    }
}
