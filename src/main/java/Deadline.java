public class Deadline extends Task {
    protected String deadline;

    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    public Deadline(String description, boolean isDone, String deadline) {
        this(description, deadline);
        this.isDone = isDone;
    }

    /**
     * Getter for this Task's deadline.
     *
     * @return Deadline for this task.
     */
    public String getDeadline() {
        return deadline;
    }
}
