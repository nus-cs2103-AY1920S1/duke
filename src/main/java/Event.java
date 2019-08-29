public class Event extends Task {
    private String timeOfEvent;
    public Event(String description, String time) {
        super(description);
        this.timeOfEvent = time;
    }

    public String toString() {
        return "[E]" + this.getStatusIcon() + this.description + " (at: " + this.timeOfEvent + ")";
    }
}

