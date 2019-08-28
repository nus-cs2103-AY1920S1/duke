package duke;

import java.time.format.DateTimeFormatter;

abstract class Task {
    static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    private String description;
    private boolean isDone;
    protected static final String storageStringSeparator = " | ";

    Task(final String description, final boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    String getDescription() {
        return description;
    }

    String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or cross symbols
    }

    boolean isDone() {
        return this.isDone;
    }

    void markAsDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }

    abstract String toStorageString();
}
