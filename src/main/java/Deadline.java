import java.time.LocalDateTime;

public class Deadline extends Task {
    protected LocalDateTime deadline;

    public Deadline(String description, LocalDateTime deadline) {
        super(description);
        this.deadline = deadline;
    }

    public Deadline(String description, boolean isDone, LocalDateTime deadline) {
        this(description, deadline);
        this.isDone = isDone;
    }

    /**
     * Getter for this Task's deadline.
     *
     * @return Deadline for this task.
     */
    public LocalDateTime getDeadline() {
        return deadline;
    }
}
