package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline which is a task needed to be done by a given date and time.
 */
public class Deadline extends Task {

    private LocalDateTime by;

    /**
     *  Constructs a deadline by a given date and time with a description.
     *
     * @param description description of deadline.
     * @param by date and time of deadline.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns a simplified summary of this deadline.
     * @return simplified string representation.
     */
    @Override
    public String getSimplifiedRepresentation() {
        return "D | " + (super.isDone ? 1 : 0) + " | " + super.taskName + " | "
                + DateTimeFormatter.ofPattern("d/M/yyyy HHmm").format(this.by);
    }

    /**
     * Returns an expressive string representation of this deadline.
     * @return expressive string representation.
     */
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
