package duke.task;

import java.util.Date;

/**
 * Represents a task of the type Event.
 */
public class Event extends Task {

    /**
     * Creates a new instance of <code>Event</code> labeled "E".
     *
     * @param s description of the task.
     * @param t time of the task.
     */
    public Event(String s, Date t) {
        super(s, t);
        this.label = "E";
    }

    /**
     * Returns a line with details of the <code>Event</code> task.
     *
     * @return String representing the task.
     */
    @Override
    public String toString() {
        return "[" + this.label + "]" + this.getStatusIcon() + this.description + " (at: " + this.getTimeAsString() + ")";
    }

}
