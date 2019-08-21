import java.time.LocalDateTime;

public class Deadline extends Task {
    protected LocalDateTime deadline;

    public Deadline(String description, LocalDateTime deadline) {
        super(description);
        this.deadline = deadline;
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
