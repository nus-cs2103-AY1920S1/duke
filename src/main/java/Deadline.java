/**
 * Encapsulates a deadline-type task handled by Duke.
 *
 * A deadline differs from other tasks because it has a due date.
 *
 * @author atharvjoshi
 * @version CS2103 AY19/20 Sem 1 iP Week 2
 */
public class Deadline extends Task {
    /** The date by which task is due */
    private String dueDate;

    /**
     * Creates a deadline with a description and due date.
     *
     * @param description string representing the description of the deadline.
     * @param dueDate string representing date by which deadline is due.
     */
    public Deadline(String description, String dueDate) {
        super(description);
        this.dueDate = dueDate;
    }

    /**
     * Return a string representation of this deadline.
     *
     * @return string representing this deadline.
     */
    public String toString() {
        return "[D]" + super.toString() + "(by:" + dueDate + ")";
    }
}
