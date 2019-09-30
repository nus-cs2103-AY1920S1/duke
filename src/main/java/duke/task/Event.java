package duke.task;

import duke.main.Parser;

import java.util.Date;

/**
 * Event is a task that happens at certain date.
 */
public class Event extends Task {
    private Date eventAt;

    /**
     * Creates a new Event with a description and Date of Event.
     *
     * @param description The task's description.
     * @param eventAt The Date of the event.
     */
    public Event(String description, Date eventAt) {
        super(description);
        this.eventAt = eventAt;
    }

    /**
     * Returns a string of an Event that can contain
     * its type, completion status, description, time and PriorityLevel.
     *
     * @return A string that contains information about an Event.
     */
    public String saveInfo() {
        return "event" + " " + description + " /at " + Parser.inputDateFormat.format(eventAt)
                + System.getProperty("line.separator") + completed
                + System.getProperty("line.separator") + priority.toString();
    }

    /**
     * Returns a string containing full information of the Event.
     * This includes type, completion status, description, time
     * and PriorityLevel.
     *
     * @return A string representation of this Event.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.completed) {
            sb.append("[E][✓] ");
        } else {
            sb.append("[E][✗] ");
        }
        sb.append(description);
        sb.append(" (");
        sb.append(Parser.outputDateFormat.format(eventAt));
        sb.append(")");
        sb.append(" ");
        sb.append(priority.toString());
        return sb.toString();
    }
}
