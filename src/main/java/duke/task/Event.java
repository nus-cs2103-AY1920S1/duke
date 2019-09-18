package duke.task;

import duke.main.Parser;

import java.util.Date;

/**
 * Event is a task that happens at certain date.
 *
 */
public class Event extends Task {
    private Date eventAt;

    public Event(String description, Date eventAt) {
        super(description);
        this.eventAt = eventAt;
    }

    /**
     * Returns a string of a task that can contain
     * its description, time and completion status.
     *
     * @return string that contains information about a task.
     */
    public String saveInfo() {
        return "event" + " " + description + " /at " + Parser.inputDateFormat.format(eventAt)
                + System.getProperty("line.separator") + completed
                + System.getProperty("line.separator") + priority.toString();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.completed) {
            sb.append("[E][\u2713] ");
        } else {
            sb.append("[E][\u2717] ");
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
