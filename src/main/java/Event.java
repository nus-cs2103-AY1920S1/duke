public class Event extends Task {
    String by;

    public Event(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    protected String formatToWrite() {
        if (this.done) {
            return String.format("E | %d | %s | %s", 1, this.description, this.by);
        } else {
            return String.format("E | %d | %s | %s", 0, this.description, this.by);
        }
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), this.by);
    }
}
