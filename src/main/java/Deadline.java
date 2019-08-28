import java.time.LocalDateTime;

/**
 * Deadline extends Task.
 */
public class Deadline extends Task {

    protected String by;

    /**
     * Deadline extends Task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        this.pattern = LocalDateTime.parse(by.trim(), dateTimeFormatter);

    }

    /**
     * Deadline extends Task.
     */
    public Deadline(String description, String by, String isDone) {
        super(description, isDone);
        this.by = by;
        this.pattern = LocalDateTime.parse(by.trim(), dateTimeFormatter);
    }

    @Override
    public String getFormatToFile() {
        return String.format("D | %d | %s | %s \n", (isDone ? 1 : 0), description, by.trim());
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + getTime() + ")";
    }
}