package duke.tasks;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Event tasks are tasks that start at a specific time and ends at a specific time.
 */
public class Event extends Task {
    Date date;

    /**
     * Initialises Event with task and date.
     *
     * @param task task
     * @param date date
     */
    public Event(String task, Date date) {
        super(task);
        this.date = date;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss");
        String iconForDone = done ? "\u2713" : "\u2718";
        return String.format("[E][%s] %s (at: %s)", iconForDone, this.task, sdf.format(this.date));
    }
}