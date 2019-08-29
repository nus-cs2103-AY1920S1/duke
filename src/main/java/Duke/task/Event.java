package duke.task;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * An Event task is a Task which the user wishes to be done by a certain deadline.
 */
public class Event extends Task {
    Date by;

    /**
     * Constructs a new Event task.
     *
     * @param description the description of the event task.
     * @param by the deadline of the event task.
     */
    public Event(String description, Date by) {
        super(description);
        this.by = by;
    }

    @Override
    public String formatToWrite() {
        if (this.done) {
            return String.format("E | %d | %s | %s", 1, this.description, new SimpleDateFormat("dd/MM/yyyy HHmm").format(this.by));
        } else {
            return String.format("E | %d | %s | %s", 0, this.description, new SimpleDateFormat("dd/MM/yyyy HHmm").format(this.by));
        }
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), new SimpleDateFormat("dd/MM/yyyy HHmm").format(this.by));
    }
}
