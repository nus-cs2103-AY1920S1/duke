public class Event extends Task {
    String eventDuration;


    public Event(String description, String eventDuration) {
        super(description);
        this.eventDuration = eventDuration;
    }

    @Override
    public String toString() {
        if (this.isDone) {
            return "[E][\u2713] " + this.description + " (at: " + this.eventDuration + ")";
        } else {
            return "[E][\u2718] " + this.description + " (at: " + this.eventDuration + ")";
        }
    }
}
