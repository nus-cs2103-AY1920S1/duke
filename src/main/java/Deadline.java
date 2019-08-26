import java.time.LocalDateTime;

public class Deadline extends Task {
    protected LocalDateTime byFormat;
    protected String by;

    public Deadline(String description, String by) {
        super(description, by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + getTime() + ")";
    }
}