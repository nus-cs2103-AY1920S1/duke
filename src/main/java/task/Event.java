package task;

import misc.Parser;

/**
 * Creates a task that start at a specific time and ends at a specific time.
 */
public class Event extends Task {
    public String unformattedDateTime;
    private String formattedDateTime;

    Event(String desc, String dateTime) {
        super(desc);
        unformattedDateTime = dateTime;

        Parser parser = new Parser();
        this.formattedDateTime = parser.convertStringToTime(dateTime, "event");
    }

    /**
     * Constructs a Event task with its description, deadline and completion status.
     * This constructor is used only when a Event task is read from a local save file.
     * @param desc The description of the event.
     * @param dateTime The event period. Consists of a single date but with start time and end time.
     * @param isDone The completion status of the Event task.
     */
    public Event(String desc, String dateTime, boolean isDone) {
        super(desc, isDone);
        unformattedDateTime = dateTime;

        Parser parser = new Parser();
        this.formattedDateTime = parser.convertStringToTime(dateTime, "event");
    }

    @Override
    public String toString() {
        return String.format("[E][%s] %s (at: %s)", getStatusIcon(), description, formattedDateTime);
    }
}
