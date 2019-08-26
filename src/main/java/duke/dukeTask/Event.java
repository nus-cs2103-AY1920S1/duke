package duke.dukeTask;

public class Event extends Task {
    private String eventPeriod;

    public Event(String description, int isDone, String eventPeriod) {
        super(description, isDone);
        this.type = "E";
        this.eventPeriod = eventPeriod;
    }

    public String getEventPeriod() {
        return eventPeriod;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + eventPeriod + ")";
    }
}
