import java.time.LocalDateTime;

/**
 * A task that takes in one LocalDateTime as the deadline.
 */
public class Deadline extends Task {

    protected LocalDateTime deadline;

    /**
     * Constructs an unfinished Deadline object.
     *
     * @param description Description of the task.
     * @param deadline Date and time by which the task is due.
     */
    public Deadline(String description, LocalDateTime deadline) {
        super(description);
        this.deadline = deadline;
    }

    /**
     * Constructs a Deadline object.
     *
     * @param description Descriptio of the task.
     * @param deadline Date and time by which the task is due.
     * @param isDone True if the task is finished.
     */
    public Deadline(String description, LocalDateTime deadline, boolean isDone) {
        super(description, isDone);
        this.deadline = deadline;
    }

    /**
     * Marks the task as done.
     *
     * @return New finished task.
     */
    @Override
    public Deadline finish() {
        return new Deadline(description, this.deadline,true);
    }

    /**
     * ToString method for printing.
     *
     * @return String representation of the deadline object.
     */
    @Override
    public String toString() {
        TimeManager tm = new TimeManager();
        return "[D]" + "[" + this.getStatusIcon() + "] " + this.description +
                " (by: " + tm.printTime(deadline) + " )\n";
    }
}