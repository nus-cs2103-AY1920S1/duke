package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Deadline extends Task {

    private LocalDateTime by;

    /**
     * Creates a Deadline object.
     *
     * @param description Description of task.
     * @param by          Date of task.
     */
    public Deadline(String description, String by) {
        super(description);

        this.by = LocalDateTime.parse(by,
            DateTimeFormatter
                .ofPattern("[d/MM/yyyy HHmm][dd/M/yyyy HHmm][d/M/yyyy HHmm][dd/MM/yyyy HHmm]"));

    }

    /**
     * Returns the original date string.
     *
     * @return Original date string.
     */
    public String getDate() {
        return by.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm", Locale.US));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by
            .format(DateTimeFormatter.ofPattern("MMMM dd, yyyy 'at' HHmm", Locale.US)) + ")";
    }
}
