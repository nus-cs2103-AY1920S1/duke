import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDateTime deadline;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");

    public Deadline(String taskName, LocalDateTime deadline) {
        super(taskName);
        this.deadline = deadline;
    }

    public Deadline(Status status, String taskName, LocalDateTime deadline) {
        super(status, taskName);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline.format(formatter) + ")" + "\n";
    }

    public String toSaveString() {
        return "D|" + (super.completed == Status.INCOMPLETE ? "0" : "1") + "|" + taskName + "|"
                + deadline.format(formatter);
    }
}
