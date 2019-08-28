public class Event extends Task {

    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    public String formattedString() {
        return String.format("D | %d | %s | %s", super.isDone ? 1 : 0, super.description, at);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}