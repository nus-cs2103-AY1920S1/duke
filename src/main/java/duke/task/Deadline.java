package duke.task;

<<<<<<< HEAD
public class Deadline extends duke.task.Task {
    /** Deadline of the task. */
=======
/**
 * Deadline class to create the deadline task.
 *
 * @author TeoShyanJie
 *
 */
public class Deadline extends Task {
    /** Deadline of the task */
>>>>>>> origin/branch-A-JavaDoc
    protected String by;

    /**
     * Constructor of the Deadline class.
     * @param description Description of the deadline task.
     * @param by Deadline of the task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Return the task as string.
     * @return String of the task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}