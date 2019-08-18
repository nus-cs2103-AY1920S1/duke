/** A class representing a event task. */
public class Event extends Task {
    private String time;

    /**
     * Constructor for the event task.
     * @param description the description of the event.
     * @param time the time at which the event starts.
     */
    public Event(String description, String time) {
        super(description);
        this.time = time;
    }

    /**
     * Returns a string representaion of the task.
     * @return a string representation of the task consisting of the task type,
     *         status, description and time.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + time + ")";
    }
}
