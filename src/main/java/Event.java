/**
 * Represents a task of the type Event.
 */
public class Event extends Task {
    //private String timeOfEvent;

    /**
     * Creates a new instance of Event.
     *
     * @param s stores the description of the task.
     * @param time stores the time of the event.
     */
    public Event(String s, String time) {
        super(s, time);
        //this.timeOfEvent = time;
    }

    //private String timeOfEvent;
    public Event(String description) {
        super(description);
        //this.timeOfEvent = time;
    }

    /**
     * Returns a line with details of the Event task.
     *
     * @return a String with details of the task.
     */
    @Override
    public String toString() {
        return "[E]" + this.getStatusIcon() + this.getDescription() + " (at: " + this.getTimeLabel() + ")";
    }
}

