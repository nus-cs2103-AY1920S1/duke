package task;

import logic.Parser;

import java.time.LocalDateTime;

public class Events extends Task {
    private LocalDateTime start;
    private LocalDateTime end;

    public Events(boolean done, String description, LocalDateTime start, LocalDateTime end) {
        super(done, description);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toFileString() {
        StringBuilder sb = new StringBuilder("E | ");
        if (isDone) {
            sb.append("1 | ");
        } else {
            sb.append("0 | ");
        }
        sb.append(description + " | " + Parser.toFileDateTime(start) + " - " + Parser.toFileDateTime(end));
        return sb.toString();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[E]");
        if (isDone) {
            sb.append("[✓] ");
        } else {
            sb.append("[✗] ");
        }
        sb.append(description);
        sb.append(" (" + Parser.printDate(start) + " - " + Parser.printDate(end) + ")");
        return sb.toString();
    }
}

