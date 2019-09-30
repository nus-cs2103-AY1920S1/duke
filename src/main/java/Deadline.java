/**
 * Represents a task of type Deadline which contains a date and time
 * of the deadline.
 */

public class Deadline extends Task {
    private String deadline;

    /**
     * Creates a Deadline task.
     * @param description of this task
     * @param deadline of this task
     */
    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return ("[D]" + super.toString() + "(by:" + this.deadline + ")" + super.formatTags());
    }
}