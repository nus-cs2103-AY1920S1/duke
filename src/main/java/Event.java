public class Event extends Task {
    private String timeOfEvent;
    public Event(String s, String time) {
        super(s);
        this.timeOfEvent = time;
    }

    public String toString() {
        return "[E]" + this.getStatusIcon() + this.description + " (at: " + this.timeOfEvent + ")";
    }
}

