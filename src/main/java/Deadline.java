import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    @Override
    public String getSimplifiedRepresentation() {
        return "D | " + (super.isDone ? 1 : 0) + " | " + super.taskName + " | "
                + DateTimeFormatter.ofPattern("d/M/yyyy HHmm").format(this.by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + (this.by.getDayOfMonth() % 10 == 1
                ? DateTimeFormatter.ofPattern("d'st of' MMMM yyyy, h.mma").format(this.by)
                : this.by.getDayOfMonth() % 10 == 2
                    ? DateTimeFormatter.ofPattern("d'nd of' MMMM yyyy, h.mma").format(this.by)
                    : this.by.getDayOfMonth() % 10 == 3
                        ? DateTimeFormatter.ofPattern("d'rd of' MMMM yyyy, h.mma").format(this.by)
                        : DateTimeFormatter.ofPattern("d'th of' MMMM yyyy, h.mma").format(this.by)) + ")";
    }
}
