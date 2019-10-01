package duke.task;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * An Event task is a Task which the user wishes to be done by a certain deadline.
 */
public class Event extends Task {
    Date by;
    SimpleDateFormat formatter;

    /**
     * Constructs a new Event task.
     *
     * @param description the description of the event task.
     * @param by the deadline of the event task.
     */
    public Event(String description, Date by) {
        super(description);
        this.by = by;
        formatter = new SimpleDateFormat("dd/MM/yyyy HHmm");
    }

    @Override
    public String formatToWrite() {
        if (super.isDone) {
            return String.format("E | %d | %s | %s", 1, this.description, formatter.format(this.by));
        } else {
            return String.format("E | %d | %s | %s", 0, this.description, formatter.format(this.by));
        }
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), formatter.format(this.by));
    }
}
