package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDateTime parsedAt;
    protected String formattedAt;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM hh:mm a");

    /**
     * Class constructor.
     * @param description Description of task
     * @param parsedAt LocalDateTime instance for when the event will be
     */
    public Event(String description, LocalDateTime parsedAt) {
        super(description, TaskType.EVENT);
        this.parsedAt = parsedAt;
        this.formattedAt = parsedAt.format(formatter);
    }

    /**
     * Class constructor with completion specified.
     * @param description Description of task
     * @param isDone Boolean for whether task is completed
     * @param parsedAt LocalDateTime instance for when the event will be
     */
    public Event(String description, boolean isDone, LocalDateTime parsedAt) {
        super(description, isDone, TaskType.EVENT);
        this.parsedAt = parsedAt;
        this.formattedAt = parsedAt.format(formatter);
    }

    /**
     * Serialize into the following format for storage, E.g. "E | 0 | project meeting | 2007-12-03T10:15:30".
     */
    @Override
    public String serialize() {
        return String.format("E | %d | %s | %s\n", isDone ? 1 : 0, description, parsedAt.toString());
    }

    /**
     * Overridden toString method to display to user, E.g. "3.[E][✗] project meeting (at: 2007-12-03T10:15:30)".
     */
    @Override
    public String toString() {
        return "[E]" + "[" + super.getStatusIcon() + "] " + super.toString() + " (at: " + formattedAt + ")";
    }
}
