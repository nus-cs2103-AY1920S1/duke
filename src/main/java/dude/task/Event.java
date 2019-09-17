package dude.task;

public class Event extends Task {
    private String eventPeriod;

    /**
     * Initializes an Event object.
     *
     * @param description Description of the Event.
     * @param isDone Completion status of the Event task.
     * @param eventPeriod Time when the Event occurs.
     */
    public Event(String description, int isDone, String eventPeriod) {
        super(description, isDone);
        this.type = "E";
        this.eventPeriod = eventPeriod;
    }

    public String getEventPeriod() {
        return eventPeriod;
    }

    public void setEventPeriod(String eventPeriod) {
        this.eventPeriod = eventPeriod;
    }

    @Override
    public String toString() {
        return "[E] " + super.toString() + " (at: " + eventPeriod + ")";
    }
}
