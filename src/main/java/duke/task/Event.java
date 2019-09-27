package duke.task;

import duke.parser.exceptions.InvalidDateTimeException;
import duke.parser.DateHandler;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

/**
 * Represents Event Tasks.
 * Instances are tasks with at a specific time.
 */
public class Event extends Task {
    /** Time of event. */
    private String at;
    /** LocalDateTime of the event. */
    private LocalDateTime datetime;

    /**
     * Creates an instance of Event.
     *
     * @param description Description of event.
     * @param at Time of the event.
     * @throws InvalidDateTimeException If DateTime format is wrong.
     */
    public Event(String description, String at) throws InvalidDateTimeException {
        super(description);
        this.at = at;
        try {
            datetime = DateHandler.getInstance().parse(at);
        } catch (DateTimeParseException e) {
            throw new InvalidDateTimeException("Event date not valid.");
        }
    }

    @Override
    public LocalDateTime getDate() {
        return datetime;
    }

    /**
     * Returns the string representation for data file.
     *
     * @return Returns String representation for data file.
     */
    @Override
    public String toFileAsString() {
        return String.format("E - %s - %s - %s", isDone ? "1" : "0", description, at);
    }

    /**
     * Returns the timing of the event.
     * @return String time;
     */
    public String getAt() {
        return at;
    }

    /**
     * Returns String representation of the Event.
     * @return String representation of the Event.
     */
    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)",
                super.toString(),
                DateHandler.getInstance().format(datetime));
    }
}
