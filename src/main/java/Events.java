import java.time.LocalDateTime;

public class Events extends Task {
    private LocalDateTime start;
    private LocalDateTime end;

    public Events(String description, LocalDateTime start, LocalDateTime end) {
        super(description);
        this.start = start;
        this.end = end;
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
