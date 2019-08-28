package duke.todo;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDateTime deadline;
    private DateTimeFormatter formatter;

    public Deadline(String description, String deadline) {
        super(description);

        this.formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        this.deadline = LocalDateTime.parse(deadline, formatter);
    }

    @Override
    public String getFormattedTask() {
        return "D | " + super.getDescription() +
                " /by " + deadline.toString();
    }
    @Override
    public String toString() {
        return "[D]" + getStatusIcon() + this.getDescription() +
                " (by: " + deadline.format(formatter) + ")";
    }
}
