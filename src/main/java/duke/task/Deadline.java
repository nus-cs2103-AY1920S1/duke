package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    private String by;
    private LocalDateTime dateTime;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        DateTimeFormatter f = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        try {
            dateTime = LocalDateTime.parse(by, f);
        } catch (DateTimeParseException e) {
        }
    }

    @Override
    public String format() {
        return "D | " + this.getStatusIcon() + " | " + this.getDescription() + " | " + this.by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
