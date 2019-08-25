package jermi.task;

/**
 * A representation of the deadline task.
 */
public class Deadline extends TaskWithDateTime {

    /**
     * Public constructor for class.
     * Creates an uncompleted deadline task.
     *
     * @param description Description of task.
     * @param deadline Deadline of task.
     */
    public Deadline(String description, String deadline) {
        this(description, deadline, "0");
    }

    /**
     * Public constructor for class.
     * Creates a completed/uncompleted deadline task.
     *
     * @param description Description of task.
     * @param deadline Deadline of task.
     * @param isDone "1" for completed task, else "0".
     */
    public Deadline(String description, String deadline, String isDone) {
        super(description, deadline, isDone);
    }

    /**
     * Returns task type code, "D".
     *
     * @return "D".
     */
    @Override
    String getTypeCode() {
        return "D";
    }

    /**
     * Returns a string representation of task.
     *
     * @return A string representation of task.
     */
    @Override
    public String toString() {
        return String.format("%s (by: %s)", super.toString(), this.getDateTime());
    }

}
