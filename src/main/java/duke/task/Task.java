package duke.task;

import java.time.format.DateTimeFormatter;

public abstract class Task {
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    static final String STORAGE_STRING_SEPARATOR = " | ";
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
     * Returns tick or cross depending on whether this Task is done.
     *
     * @return tick if this Task is done, cross otherwise
     */
    public String getStatusIcon() {
        // TODO: investigate why \u2718 crashes the GUI
        // return (isDone ? "\u2713" : "\u2718"); //return tick or cross symbols
        return (isDone ? "\u2713" : "X"); //return tick or cross symbols
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
        return "[" + getStatusIcon() + "] " + this.description;
    }

    public abstract String toStorageString();
}
