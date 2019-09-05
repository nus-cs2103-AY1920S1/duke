package duke.task;

import duke.date.DateValidator;
import duke.date.InvalidDateDukeException;

/**
 * Represents an event that is held from a start time to an end time.
 */
public class Event extends Task {

    protected String at;

    /**
     * Constructor for the event class.
     * @param description Description of the event
     * @param at Time duration of the event.
     * @throws InvalidTaskDukeException If the description is invalid
     * @throws InvalidDateDukeException If the date-time is invalid
     */
    public Event(String description, String at) throws InvalidTaskDukeException, InvalidDateDukeException {
        super(description);
        setAt(at);
    }

    /**
     * Set the time of the event, if possible.
     * @param at The time of the event
     * @throws InvalidDateDukeException If the date is invalid.
     */
    public void setAt(String at) throws InvalidDateDukeException {
        DateValidator v = new DateValidator();
        boolean isValid = v.validateDate(at, true);
        if (!isValid) {
            throw new InvalidDateDukeException("Invalid date format! Please ensure your date sticks to this format:\n"
                    + "    Deadlines : \"DD/MM/YYYY HHMM\"\n"
                    + "    Events : \"DD/MM/YYYY HHMM-HHMM\"");
        }
        this.at = at;
    }

    /**
     * Returns the time slot of the event.
     * @return
     */
    public String getTime() {
        return at;
    }

    @Override
    public String toString() {
        return "[Event] " + super.toString() + " (at: " + at + ")";
    }
}