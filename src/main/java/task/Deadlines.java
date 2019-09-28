package task;

import logic.Parser;

import java.time.LocalDateTime;

public class Deadlines extends Task {
    private LocalDateTime deadline;

    public Deadlines(boolean isDone, String description, LocalDateTime deadline) {
        super(isDone, description);
        this.deadline = deadline;
    }

    @Override
    public String toFileString() {
        StringBuilder sb = new StringBuilder("D | ");

        if (isDone) {
            sb.append("1 | ");
        } else {
            sb.append("0 | ");
        }

        sb.append(description + " | " + Parser.toFileDateTime(deadline));
        return sb.toString();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[D]");

        if (isDone) {
            sb.append("[✓] ");
        } else {
            sb.append("[✗] ");
        }

        sb.append(description);
        sb.append(" (by: " + Parser.printDate(deadline) + ")");
        return sb.toString();
    }
}
