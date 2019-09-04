public class Event extends Task {
    public Event(String description, String at) {
        super(description);
        super.date = at;
        super.type ="E";
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + super.date + ")";
    }
}

