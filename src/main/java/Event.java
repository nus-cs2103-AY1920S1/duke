public class Event extends Task {

    private String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String serialize() {
        return String.format("E | %d | %s | %s", getStatusCode(), description , at);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}