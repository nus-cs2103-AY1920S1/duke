package seedu.duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Task {
    protected String description;
    protected boolean isDone;
    protected static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/uuuu HHmm");

    public Task(boolean done, String description) {
        this.description = description;
        this.isDone = done;
    }

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task() {
        this("");
    }

    public Task markAsDone() {
        this.isDone = true;
        return this;
    }

    public String getDesc() {
        return description;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); // return tick or X symbols
    }

    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), getDesc());
    }

    public String saveFormat() {
        return String.format("| %d | %s\n", isDone ? 1 : 0, getDesc());
    }

    public static LocalDateTime parseTime(String time) throws DateTimeParseException {
        return LocalDateTime.parse(time, dtf);
    }
}
