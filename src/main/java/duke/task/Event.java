package duke.task;

import java.util.Date;

/**
 * Event is a task that happens at certain date.
 *
 */
public class Event extends Task{
    private Date eventAt;
    Event(String taskDetails, Date eventAt) {
        super(taskDetails);
        this.eventAt = eventAt;
    }

    /**
     * Returns a string of a task that can contain
     * its description, time and completion status.
     *
     * @return string that contains information about a task.
     */
    String saveInfo() {
        return "event" + " " + taskDetails + " /at " + TaskList.inputDateFormat.format(eventAt) + System.getProperty("line.separator")
                + completed;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.completed) {
            sb.append("[E][✓] ");
        } else {
            sb.append("[E][✗] ");
        }
        sb.append(taskDetails);
        sb.append (" (");
        sb.append(TaskList.outputDateFormat.format(eventAt));
        sb.append(")");
        return sb.toString();
    }
}
