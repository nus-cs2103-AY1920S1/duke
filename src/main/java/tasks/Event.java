package tasks;

import java.util.Date;
import java.text.SimpleDateFormat;

/**
 * Encapsulates an Task object of the type Event.
 * Represents a Event task that has a specific duration.
 */
public class Event extends Task {
    protected Date start;
    protected Date end;

    /**
     * Constructs an Event object.
     *
     * @param description This is the short description of the task.
     * @param start This represents the start of the event.
     * @param end This represents the end time of the event.
     */
    public Event(String description, Date start, Date end) {
        super(description);
        this.typeOfTask = "E";
        this.start = start;
        this.end = end;
    }

    /**
     * Getter function that returns the due date of the Event
     * in the format of a String.
     *
     * @return The due date of the deadline.
     */
    public String getDueInString() {
        SimpleDateFormat startFormat = new SimpleDateFormat("dd/MM/yyyy HHmm");
        SimpleDateFormat endFormat = new SimpleDateFormat(" - HHmm");
        return startFormat.format(start) + endFormat.format(end);
    }

    /**
     * Convert Event object to String format.
     *
     * @return The String format of the Event.
     */
    @Override
    public String toString() {
        String statusIcon = this.getStatusIcon();
        SimpleDateFormat startFormat = new SimpleDateFormat("dd/MM/yyyy HHmm");
        SimpleDateFormat endFormat = new SimpleDateFormat(" - HHmm");
        return ("[" + typeOfTask + "]" + "[" + statusIcon + "] "
                + description + "(at: "
                + startFormat.format(start) + endFormat.format(end) + ")");
    }
}
