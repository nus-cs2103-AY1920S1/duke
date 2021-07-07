/**
 * The DoAfter class represents a type of task.
 */
public class DoAfter extends Task {

    protected String after;

    /**
     * Creates a DoAfter task.
     *
     * @param description A string representing the description of the task
     * @param after A string representing task that need to be done
     *              after a certain time.
     */
    public DoAfter(String description, String after) {
        super(description);
        this.after = after;
    }

    /**
     * Gets the task description, time and status.
     *
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        return "[DA]" + super.toString() + " (after: " + after + ")";
    }
}
