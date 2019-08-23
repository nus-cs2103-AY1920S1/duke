import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDateTime by;

    public Deadline(String description, LocalDateTime by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("d MMMM yyyy, ha")) + ")";
    }

    @Override
    public String fileString() {
        return "D" + super.fileString() + " | " + by.format(DateTimeFormatter.ofPattern("d MMMM yyyy, ha"));
    }
}