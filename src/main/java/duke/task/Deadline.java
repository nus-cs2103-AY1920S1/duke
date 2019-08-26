package duke.task;

import duke.exception.IllegalDescriptionException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/** A class representing a task with a deadline. */
public class Deadline extends Task {
    protected LocalDateTime by;

    /**
     * Constructor for the deadline task class.
     * @param description the description of the task.
     * @param by the deadline of the task.
     */
    public Deadline(String description, LocalDateTime by) throws IllegalDescriptionException {
        super(description);
        this.by = by;
    }

    /**
     * Returns a string representation of the task to be stored in file.
     * @return a string representation of the task to be stored in file, consisting of the task type,
     *         status, description and deadline.
     */
    public String toStringForFile() {
        return super.toStringForFile() + " | " + by;
    }

    /**
     * Returns a string representaion of the task.
     * @return a string representation of the task consisting of the task type,
     *         status, description and deadline.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm");
        String dateTime = formatter.format(by);
        return "[D]" + super.toString() + " (by: " + dateTime + ")";
    }
}
