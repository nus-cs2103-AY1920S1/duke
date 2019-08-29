/**
 * Represents a task of the type Event.
 */
public class Event extends Task {

    /**
     * Creates a new instance of Event labeled "E".
     *
     * @param s description of the task.
     * @param t time of the task.
     */
    public Event(String s, String t) {
        super(s, t);
        this.label = "E";
    }

    /**
     * Returns a line with details of the Event task.
     *
     * @return String representing the task.
     */
    @Override
    public String toString() {
        return "[" + this.label + "]" + this.getStatusIcon() + this.description + " (at: " + this.time + ")";
    }

}
