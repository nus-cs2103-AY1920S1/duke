public class Event extends Task {
    protected String eventPeriod;

    public Event(String description, int isDone, String eventPeriod) {
        super(description, isDone);
        this.type = "E";
        this.eventPeriod = eventPeriod;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + eventPeriod + ")";
    }
}
