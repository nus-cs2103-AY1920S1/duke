package seedu.duke.tasks;

import java.time.LocalDateTime;

public class DeadlineTask extends Task {
    private static final long serialVersionUID = -2528903469387431L;

    private final LocalDateTime deadline;

    /**
     * Create a deadline with a deadline.
     *
     * @param description the deadline description
     * @param deadline the deadline
     */
    public DeadlineTask(String description, LocalDateTime deadline) {
        super(description);
        this.deadline = deadline;
    }

    /**
     * Get the deadline's deadline.
     *
     * @return the deadline
     */
    public LocalDateTime getDeadline() {
        return deadline;
    }

    @Override
    public String toString() {
        return String.format(
                "[D]%s%s", super.toString(),
                deadline != null
                        ? String.format(" (by: %s)", deadline.format(DATE_TIME_FORMATTER))
                        : ""
        );
    }
}
