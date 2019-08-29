public class Event extends Task {
    public Event(String description, String date) {
        super(description);
        super.date = date;
        super.type = "E";
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + super.date + ")";
    }
}

