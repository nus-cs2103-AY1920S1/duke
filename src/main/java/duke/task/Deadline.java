package duke.task;

import java.time.LocalDateTime;

public class Deadline extends Task {
    protected LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        this(description, by, false);
    }

    /**
     * Constructs a Deadline object.
     * @param description Description of deadline.
     * @param by DateTime of deadline.
     * @param isDone Whether a deadline is done.
     */
    public Deadline(String description, LocalDateTime by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    /**
     * Returns deadline of task.
     * @return Deadline of task.
     */
    public String getDeadline() {
        return DATE_TIME_FORMATTER.format(this.by);
    }

    /**
     * Returns a String representing the task, of format "[D][✘] task (by: date time)".
     * @return String representing the task.
     */
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), DATE_TIME_FORMATTER.format(this.by));
    }
}
