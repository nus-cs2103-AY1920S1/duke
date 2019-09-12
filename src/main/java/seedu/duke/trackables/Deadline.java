package seedu.duke.trackables;

public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public Deadline(String... args) {
        super(args);
        this.by = args[3];
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String getAsString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("D").append(" | ")
            .append(isDone ? "1" : "0")
            .append(" | ").append(this.description)
            .append(" | ").append(this.by);
        return sb.toString();
    }
}
