package duke.task;

import java.text.ParseException;

/**
 * Represents an event task.
 */
public class Event extends Task {

    /**
     * Initiates an Event object.
     * @param description content of event task
     */
    public Event(String description) {
        super(description);
    }

    /**
     * Initiates an Event object.
     * @param description content of event task
     * @param isDone true if task is done and false otherwise
     */
    public Event(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Expresses an Event object in natural language.
     * @return string representation of an event task
     * @throws ParseException if description of event cannot be parsed
     */
    public String repr() throws ParseException {
        return "[E][" + getStatusIcon() + "] " + super.formatDescription();
    }
}
