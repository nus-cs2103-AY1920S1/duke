import java.time.LocalDateTime;

public class Deadline extends Task {

    private LocalDateTime dueDateTime;

    public Deadline(String description, LocalDateTime dueDateTime) {
        super(description);
        this.dueDateTime = dueDateTime;
    }

    public Deadline(String description, String dueDateTime, boolean isDone) {
        super(description, isDone);
        this.dueDateTime = dueDateTime;
    }

    public String getDueDateTime() {
        return dueDateTime;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + formatter.format(dueDateTime) + ")";
    }
}
