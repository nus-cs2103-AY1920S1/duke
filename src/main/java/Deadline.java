import java.time.LocalDateTime;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        Deadline deadline1 = (Deadline) o;
        return deadline.equals(deadline1.deadline);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), deadline);
    }

}
