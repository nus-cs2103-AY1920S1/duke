import java.time.LocalDateTime;

public class Deadline extends Task {
    protected LocalDateTime byFormat;
    protected String by;

    public Deadline(String description, String by) {
        super(description, by);
    }
    public Deadline(String description, String by, String isDone) {
        super(description, isDone);
        this.by = by.trim();
    }
    @Override
    public String getFormatToFile() {
        return String.format("D | %d | %s | %s \n", (isDone ? 1 : 0), description, by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + getTime() + ")";
    }
}