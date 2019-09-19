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

    /**
     * Displays a String representation of this Event to the user.
     * @return a String representation of this Event
     */
    @Override
    public String toString() {
        return String.format("[E][%s] %s (at: %s)", getStatusIcon(), description, formattedDateTime);
    }

    /**
     * Compares if two Events are the same.
     * @param o the other object to be compared with.
     * @return a boolean.
     */
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (o instanceof Event) {
            Event otherEvent = (Event) o;

            boolean isDescEqual = description.equals(otherEvent.description);
            boolean isDateTimeEqual = unformattedDateTime.equals(otherEvent.unformattedDateTime);

            return isDescEqual && isDateTimeEqual;
        } else {
            return false;
        }
    }

    /**
     * Returns a String representation of this Event to be written in a save file.
     * @return an encoded String representation of this Event.
     */
    @Override
    public String toEncodedString() {
        int isDoneStatus = isDone ? 1 : 0;

        return String.format("D : %d : %s : %s", isDoneStatus, description, unformattedDateTime);
    }
}
