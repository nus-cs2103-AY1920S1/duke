import java.time.LocalDateTime;

public class Deadlines extends Task{
    private LocalDateTime deadline ;

    public Deadlines(String description, LocalDateTime deadline) {
        super(description);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[D]");
        if(isDone) {
            sb.append("[✓] ");
        } else {
            sb.append("[✗] ");
        }
        sb.append(description);
        sb.append(" (by: " + Parser.printDate(deadline) + ")");
        return sb.toString();
    }
}
