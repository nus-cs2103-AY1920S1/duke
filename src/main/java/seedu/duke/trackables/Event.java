package seedu.duke.trackables;

public class Event extends Task {

    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    public Event(String... args) {
        super(args);
        this.at = args[3];
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

    @Override
    public String getAsString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("E").append(" | ")
            .append(isDone ? "1" : "0")
            .append(" | ").append(this.description)
            .append(" | ").append(this.at);
        return sb.toString();
    }
}
