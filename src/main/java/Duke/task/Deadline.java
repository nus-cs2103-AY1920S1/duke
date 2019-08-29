package duke.task;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A Deadline task is a Task which the user wishes to be done by a certain deadline.
 */
public class Deadline extends Task {
    Date by;

    /**
     * Constructs a new Deadline task.
     *
     * @param description the description of the Deadline task.
     * @param by the deadline of the Deadline task.
     */
    public Deadline(String description, Date by) {
        super(description);
        this.by = by;
    }

    @Override
    public String formatToWrite() {
        if (this.done) {
            return String.format("D | %d | %s | %s", 1, this.description, new SimpleDateFormat("dd/MM/yyyy HHmm").format(this.by));
        } else {
            return String.format("D | %d | %s | %s", 0, this.description, new SimpleDateFormat("dd/MM/yyyy HHmm").format(this.by));
        }
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), new SimpleDateFormat("dd/MM/yyyy HHmm").format(this.by));
    }
}
