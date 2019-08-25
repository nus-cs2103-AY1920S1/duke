public class Event extends Task {
    protected String at;

    Event(String description, boolean isDone, String at) {
        super(description, isDone);
        this.at = at;
    }

    Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

    public String toFile() {
        return "E | " + super.getStatusIcon() + " | " + description + " | " + at;
    }
}