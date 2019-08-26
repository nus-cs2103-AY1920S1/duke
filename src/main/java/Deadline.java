/** A class representing a task with a deadline. */
public class Deadline extends Task {
    protected String by;

    /**
     * Constructor for the deadline task class.
     * @param description the description of the task.
     * @param by the deadline of the task.
     */
    public Deadline(String description, String by) throws IllegalDescriptionException {
        super(description);
        if (by.isEmpty()) {
            throw new IllegalDescriptionException("The deadline cannot be empty.");
        }
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
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
