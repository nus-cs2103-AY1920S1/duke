package duke.task;

import duke.exception.EmptyTaskDukeException;
import duke.exception.InvalidDateTimeDukeException;
import duke.exception.InvalidTaskDukeException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Represents an Event task.
 */

public class Event extends Task {
    /**
     * Represents date and time of event.
     */
    private Date atTime;

    /**
     * Constructor of Event object.
     *
     * @param name   Name of Event.
     * @param atTime atTime of Event.
     * @throws EmptyTaskDukeException   If name is empty.
     * @throws InvalidTaskDukeException If atTime input does not follow DD/MM/YYYY HHMM.
     */
    public Event(String name, String atTime) throws EmptyTaskDukeException, InvalidTaskDukeException, InvalidDateTimeDukeException {
        super(name);
        if (name == null) {
            throw new EmptyTaskDukeException("event");
        }
        if (atTime == null) {
            throw new InvalidTaskDukeException("event");
        }
        try {
            DateFormat inputFormatter = new SimpleDateFormat("dd/MM/yyyy HHmm");
            this.atTime = inputFormatter.parse(atTime);
        } catch (ParseException e) {
            throw new InvalidDateTimeDukeException("event");
        }
    }

    /**
     * Accessor to get date and time of Event.
     *
     * @return
     */
    public String getAtTime() {
        DateFormat outputFormatter = new SimpleDateFormat("dd MMMM YYYY, hh.mmaa");
        String output = outputFormatter.format(this.atTime);
        return output;
    }

    /**
     * Gives appropriate representation of Event.
     *
     * @return String representation of Event. Includes type of Task, isDone, name of Task and atTime.
     */
    @Override
    public String toString() {
        DateFormat outputFormatter = new SimpleDateFormat("dd MMMM YYYY, h.mmaa");
        String output = outputFormatter.format(this.atTime);
        StringBuilder stringBuilder = new StringBuilder("[E]");
        stringBuilder.append(super.toString());
        stringBuilder.append(" (");
        stringBuilder.append(output);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
