package duke.task;

import java.time.format.DateTimeFormatter;

/**
 * The base class of all types of Tasks.
 */
public abstract class Task {
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    private String description;
    private boolean isDone;

    Task(final String description, final boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns this Task's description.
     *
     * @return this Task's description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns true if this Task is done.
     *
     * @return true if this Task is done
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Marks this task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return this.description;
    }
}
