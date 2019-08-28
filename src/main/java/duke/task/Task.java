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

    public String getDescription() {
        return description;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or cross symbols
    }

    public boolean isDone() {
        return this.isDone;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }

    public abstract String toStorageString();
}
