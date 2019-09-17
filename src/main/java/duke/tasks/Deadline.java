package duke.tasks;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Deadlines are tasks that need to be done before a specific date/time.
 */
public class Deadline extends Task {
    Date date;

    /**
     * Initialises deadline with task and date.
     * @param task task
     * @param date date
     */
    public Deadline(String task, Date date) {
        super(task);
        this.date = date;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss");
        String iconForDone = done ? "\u2713" : "\u2718";
        return String.format("[D][%s] %s (by: %s)", iconForDone, this.task, sdf.format(this.date));
    }
}