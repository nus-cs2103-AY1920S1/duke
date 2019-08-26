package seedu.duke.tasks;

import java.time.LocalDateTime;

public class DeadlineTask extends Task {
    private final LocalDateTime deadline;

    public DeadlineTask(String description, LocalDateTime deadline) {
        super(description);
        this.deadline = deadline;
    }

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
