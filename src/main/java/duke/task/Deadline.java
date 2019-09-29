package duke.task;

import java.time.LocalDateTime;

/**
 * A task which has a deadline.
 */
public class Deadline extends Task {

    private String by;

    /**
     * A task which has a deadline.
     *
     * @param description The description.
     * @param by          The time and date.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        this.pattern = LocalDateTime.parse(by.trim(), dateTimeFormatter);
    }

    /**
     * A task which has a deadline.
     *
     * @param description The description.
     * @param by          The time and date.
     * @param isDone      The status of task.
     */
    public Deadline(String description, String by, String isDone) {
        super(description, isDone);
        this.by = by;
        this.pattern = LocalDateTime.parse(by.trim(), dateTimeFormatter);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getFormatToFile() {
        return String.format("D | %d | %s | %s \n", (isDone ? 1 : 0), description, by.trim());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + getTime() + ")";
    }
}