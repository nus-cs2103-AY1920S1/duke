package duke.task;

import java.time.LocalDateTime;

/**
 * A event task which has a keyword of "at".
 */
public class Event extends Task {

    private String at;

    public Event(String description, String at) {
        super(description);
        this.at = at.trim();
        this.pattern = LocalDateTime.parse(at.trim(), dateTimeFormatter);
    }

    public Event(String description, String at, String isDone) {
        super(description, isDone);
        this.pattern = LocalDateTime.parse(at.trim(), dateTimeFormatter);
        this.at = at;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getFormatToFile() {
        return String.format("E | %d | %s | %s \n", (isDone ? 1 : 0), description, at.trim());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + getTime() + ")";
    }
}