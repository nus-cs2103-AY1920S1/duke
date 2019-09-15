package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDateTime parsedBy;
    protected String formattedBy;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM hh:mm a");

    /**
     * Class constructor.
     * @param description Description of task
     * @param parsedBy LocalDateTime instance for the dateline
     */
    public Deadline(String description, LocalDateTime parsedBy) {
        super(description, TaskType.DEADLINE);
        this.parsedBy = parsedBy;
        this.formattedBy = parsedBy.format(formatter);
    }

    /**
     * Class constructor with completion specified.
     * @param description Description of task
     * @param isDone Boolean for whether task is completed
     * @param parsedBy LocalDateTime instance for the dateline
     */
    public Deadline(String description, boolean isDone, LocalDateTime parsedBy) {
        super(description, isDone, TaskType.DEADLINE);
        this.parsedBy = parsedBy;
        this.formattedBy = parsedBy.format(formatter);
    }

    /**
     * Serialize into the following format for storage, E.g. "D | 0 | read book | 2007-12-03T10:15:30".
     */
    @Override
    public String serialize() {
        return String.format("D | %d | %s | %s\n", isDone ? 1 : 0, description, parsedBy.toString());
    }

    /**
     * Overridden toString method to display to user, E.g. "[D][X] return book (by: 2007-12-03T10:15:30)".
     */
    @Override
    public String toString() {
        return "[D]" + "[" + super.getStatusIcon() + "] " + super.toString() + " (by: " + formattedBy + ")";
    }
}
