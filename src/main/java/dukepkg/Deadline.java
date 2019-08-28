package dukepkg;

/**
 * The task of type Deadline.
 */
public class Deadline extends dukepkg.Task{
    private final String by;

    /**
     * Instantiates a new Deadline task.
     *
     * @param task the task
     * @param by   the deadline by which the task needs to be finished.
     */
    public Deadline(String task, String by) {
        super(task);
        this.by = by;
    }

    /**
     * Gets the deadline schedule.
     *
     * @return the time
     */
    public String getBy() {
        return by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
