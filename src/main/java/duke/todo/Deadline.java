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

    /**
     * Returns a string of the currently deadline with formatting,
     * intended for saving usage.
     *
     * @return Formatted string of this deadline.
     */
    @Override
    public String getFormattedTask() {
        return "D | " + super.getDescription() +
                " /by " + deadline.toString();
    }

    /**
     * Returns a string of this deadline for display usage.
     *
     * @return Formatted string of this deadline for display.
     */
    @Override
    public String toString() {
        return "[D]" + getStatusIcon() + this.getDescription() +
                " (by: " + deadline.format(formatter) + ")";
    }
}
