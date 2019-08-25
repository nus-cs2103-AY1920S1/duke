package duck.task;

import java.time.LocalDateTime;

public class Deadline extends Task {

    private LocalDateTime dueDateTime;

    public Deadline(String description, LocalDateTime dueDateTime) {
        super(description);
        this.dueDateTime = dueDateTime;
    }

    public Deadline(String description, LocalDateTime dueDateTime, boolean isDone) {
        super(description, isDone);
        this.dueDateTime = dueDateTime;
    }

    public LocalDateTime getDueDateTime() {
        return dueDateTime;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + formatter.format(dueDateTime) + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Deadline) {
            Deadline another = (Deadline) obj;
            return this.description.equals(another.description) && this.dueDateTime.equals(another.dueDateTime)
                    && this.isDone == another.isDone;
        } else {
            return false;
        }
    }
}
