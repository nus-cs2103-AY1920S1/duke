package duke.task;

import static duke.util.DateTime.parseDate;

import duke.util.exception.DukeException;
import java.util.Date;

/**
 * Represents an Event, a type of Task that has an expected date of occurrence.
 */
public class EventTask extends Task {
    private Date at;

    /**
     * Constructs a new Event, with the specified description and date of occurrence.
     * @param description Description of the Event.
     * @param atString String representing expected date of occurrence of the Event.
     */
    public EventTask(String description, String atString) throws DukeException {
        super(description);
        this.type = TaskType.EVENT;

        this.at = parseDate(atString);
    }

    /**
     * Returns the String representation of an Event for display purposes.
     * Adds the date of occurrence of the Event to the String representation
     * provided by the Task class.
     * @return String representation of a Event for display purposes.
     */
    @Override
    public String toString() {
        return super.toString() + " (at: " + at + ")";
    }

    @Override
    public void snooze() {
        // todo
    }
}
