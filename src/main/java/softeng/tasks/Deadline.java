package softeng.tasks;

import java.util.Objects;
import softeng.date.Date;

/**
 * Represents a Deadline task. A <code>Deadline</code> object corresponds to the
 * description of the task and the due date. e.g. <code>read book by Saturday</code>
 */
public class Deadline extends Task {
    protected String by;
    protected Date byDate;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }
    public Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    public Deadline(String description, Date byDate) {
        super(description);
        this.byDate = byDate;
    }

    @Override
    public String toString() {
        String str = Objects.isNull(byDate) ? by : byDate.toString();
        return "[D]["+ this.getStatusIcon() +"] " + super.toString() + " (by: " + str + ")";
    }

    @Override
    public String toSave() {
        String done = isDone ? "1 | " : "0 | ";
        return "D | " + done + description + " | " + by;
    }
}
