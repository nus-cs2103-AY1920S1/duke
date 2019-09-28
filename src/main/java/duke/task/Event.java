package duke.task;

import duke.util.DateManager;
import duke.util.DukeException;

import java.util.Objects;
import java.util.Date;

/**
 * Represents a task that start at a specific time and ends at a specific time.
 */
public class Event extends Task {
    /** Date whereby the event starts. */
    private Date eventDate;

    /**
     * Constructor for event task.
     *
     * @param description Description of event.
     * @param eventDateString String specifying the date whereby the event starts.
     * @throws DukeException If eventDateString is not in the correct format.
     */
    public Event(String description, String eventDateString) throws DukeException {
        super(description);

        assert !eventDateString.equals("") : "Description of Task should not be empty.";

        this.eventDate = DateManager.stringToDate(eventDateString);
    }

    /**
     * Returns the print format of the event.
     *
     * @return String representation of the event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + DateManager.dateToString(eventDate) + ")";
    }

    /**
     * Returns the save format of the event.
     *
     * @return Formatted string to be saved in storage file.
     */
    @Override
    public String getSaveString() {
        return "E | " + (isDone ? 1 : 0) + " | " + description + " | "
                + DateManager.dateToString(eventDate);
    }

    public Date getEventDate() {
        return eventDate;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof Event)) {
            return false;
        }

        Event event = (Event) obj;
        return this.description.equals(event.getDescription())
                && this.eventDate.equals(event.getEventDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.description, this.eventDate);
    }
}
