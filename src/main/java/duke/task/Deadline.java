package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Deadline object class.
 */
public class Deadline extends Task {

    final String TYPE_DEADLINE = "D";
    private LocalDateTime dateTime;

    public Deadline(String taskName, LocalDateTime dateTime) {
        super(taskName);
        this.dateTime = dateTime;
    }

    @Override
    public String getType() {
        return TYPE_DEADLINE;
    }

    public String getDateTime() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd MMM yyyy HHmm");
        return this.dateTime.format(dtf);
    }


    @Override
    public String toString() {
        return String.format("[%s][%s] %s (by: %s)",
                this.getType(), isDone ? "\u2713" : "\u2718",
                this.taskName, getDateTime());
    }
}
