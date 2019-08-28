package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Deadline class. Provides a framework for new Deadline objects.
 */
public class Deadline extends Task {

    /** Stores the date and time of the deadline. */
    protected LocalDateTime deadline;
    /** Stores the formatter by which the date and time is printed. */
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");

    /**
     * Creates a new Deadline object.
     * @param taskName The name of the deadline task.
     * @param deadline The due date and time of the deadline task.
     */
    public Deadline(String taskName, LocalDateTime deadline) {
        super(taskName);
        this.deadline = deadline;
    }

    /**
     * Creates a new Deadline object with its done status pre-determined.
     * @param status The status of the deadline object.
     * @param taskName The name of the deadline task.
     * @param deadline The due date and time of the deadline task.
     */
    public Deadline(Status status, String taskName, LocalDateTime deadline) {
        super(status, taskName);
        this.deadline = deadline;
    }

    /**
     * Returns a string describing the deadline task.
     * @return A string describing the deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline.format(formatter) + ")" + "\n";
    }

    /**
     * Returns a string in the format to be saved to disk.
     * @return A string in the format to be saved to disk.
     */
    public String toSaveString() {
        return "D|" + (super.completed == Status.INCOMPLETE ? "0" : "1") + "|" + taskName + "|"
                + deadline.format(formatter);
    }
}
