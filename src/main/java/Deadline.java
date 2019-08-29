import java.time.LocalDateTime;

public class Deadline extends Task {

    protected LocalDateTime deadline;

    public Deadline(String description, LocalDateTime deadline) {
        super(description);
        this.deadline = deadline;
    }
    public Deadline(String description, LocalDateTime deadline, boolean isDone) {
        super(description, isDone);
        this.deadline = deadline;
    }

    @Override
    public Deadline finish() {
        return new Deadline(description, this.deadline,true);
    }

    @Override
    public String toString() {
        TimeManager tm = new TimeManager();
        return "[D]" + "[" + this.getStatusIcon() + "] " + this.description + " (by: " + tm.printTime(deadline) + " )\n";
    }
}