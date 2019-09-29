import java.time.LocalDateTime;

/**
 * Deadline task.
 */
public class Deadline extends Task {

    protected LocalDateTime by;

    /**
     * Constructor for Deadline.
     *
     * @param description representing the task description
     * @param by          representing the time of the dateline
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
        this.type = "D";
    }

    /**
     * Constructor for Deadline.
     *
     * @param description representing the task description
     * @param done        representing if the task is done
     * @param by          representing the time of the deadline
     */
    public Deadline(String description, String done, LocalDateTime by) {
        super(description, done);
        this.by = by;
        this.type = "D";
    }

    /**
     * Method to return the deadline time.
     *
     * @return A String which represents the time
     */
    public String getBy() {
        return by.format(OUT_DTF);
    }

    /**
     * Override toString method.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(OUT_DTF) + ")";
    }
}