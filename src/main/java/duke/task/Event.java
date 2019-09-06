package duke.task;

import duke.time.DateTime;

/**
 * Represents an event object.
 */
public class Event extends Task {
    private String dateTime;

    /**
     * Constructs an event object.
     * @param description Description of the event.
     * @param dateTime Date and time of the event.
     */
    public Event(String description, String dateTime) {
        super(description);
        this.dateTime = dateTime;
        assert (!dateTime.isEmpty()) : "You need to input the event date and time";
    }

    // Trying out using varargs.
    public Event(String... strings) {
        super(strings[0]);
        this.dateTime = strings[1];
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: "
                + DateTime.parseDateTime(dateTime) + ")";
    }

    /**
     * Converts the Event object to a String representing it for storage purposes.
     * @return
     */
    @Override
    public String toStorage() {
        int isDoneInt = isDone ? 1 : 0;
        return String.format("%d | E | %s | %s", isDoneInt, description, dateTime);
    }
}
