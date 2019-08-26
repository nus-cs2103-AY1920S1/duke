public class Event extends Task {
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    // [Level-7] Converts task to String format to write to hard disk
    public String convertTaskToString() {
        return String.format("E | %s | %s | %s", this.getStatus(), this.description, this.at);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
