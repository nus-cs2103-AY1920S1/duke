package duke.task;

import duke.calendar.Date;
import duke.calendar.Time;

/**
 * Represents a deadline task.
 * A <code>Deadline</code> object corresponds to a type of <code>Task</code> object with a due date and an optional
 * time that it is due.
 */
public class Deadline extends Task {
    protected Date date;
    protected Time time;

    /**
     * Constructor for <code>Deadline</code>.
     * @param description Description for the deadline task
     * @param date Date that the task is due.
     * @param time Time that the task is due.
     */
    public Deadline(String description, Date date, Time time) {
        super(description);
        this.date = date;
        this.time = time;
    }

    /**
     * Returns the unprocessed due date of the task.
     * @return Unprocessed date.
     */
    public String getUnprocessedDate() {
        return date.getUnprocessedDate();
    }

    /**
     * Returns the unprocessed due time of the task.
     * @return Unprocessed time.
     */
    public String getUnprocessedTime() {
        return time.getUnprocessedTime();
    }

    /**
     * Returns the type of task that this <code>Deadline</code> object is.
     * @return Deadline type.
     */
    public String getType() {
        return "D";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + date.toString() + (time.toString().equals("") ? "" : ", "
                + time.toString()) + ")";
    }
}
