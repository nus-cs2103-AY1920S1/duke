package duke.task;

import java.util.Date;

/**
 * Create a Deadline Task. Description and timing required.
 */
public class Deadline extends Task {
    private String by;
    private Date date;

    /**
     * Create a Deadline Task. Description and timing required.
     *
     * @param description Description of the task, in String.
     * @param by Timing of the task, in String.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        date = new Date(by);
    }

    @Override
    public String toWriteFile() {
        return "D | " + getDoneStatus() + " | " + description + " | " + by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + date.toString() + ")";
    }
}
