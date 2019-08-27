/**
 * Represents a Deadline task.
 */
public class Deadline extends Task {

    private String deadline;

    /**
     * Creates a Deadline task with the associated description and deadline.
     * @param desc Describes the deadline.
     * @param deadline The deadline that the task is due by.
     */
    public Deadline(String desc, String deadline) {
        super(desc);
        this.deadline = deadline;
    }

    public Deadline(String desc, boolean isDone, String deadline) {
        super(desc, isDone);
        this.deadline = deadline;
    }

    public String getDeadline() {
        return this.deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return String.format("[%s][%s] %s (by: %s)", "D", super.getDoneSymbol(), this.desc, this.deadline);
    }

}
