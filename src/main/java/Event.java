public class Event extends Task {
    protected DateAndTime at;

    public Event(String description, String at) {
        super(description);
        this.at = new DateAndTime(at);
    }

    @Override
    public String toSave() {
        return "E | " + (isDone ? 1 : 0) + " | " + description + " | " + at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
