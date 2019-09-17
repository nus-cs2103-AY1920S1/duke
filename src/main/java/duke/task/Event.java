package duke.task;

import duke.datetime.Date;
import duke.datetime.Timing;
import duke.dukeexception.DukeException;

/**
 * Represents an actual event that the user has to attend or complete.
 */
public class Event extends Task {
    private Date date;
    private Timing timing;

    /**
     * Class constructor that specifies description, completion status
     * and timing of the event.
     *
     * @param description Description of event.
     * @param date Date of event.
     * @param timing Timing of Event.
     * @param done Completion status of event.
     */
    public Event(String description,String date, String timing, int done) throws DukeException {
        super(description, done);
        this.date = new Date(date);
        this.timing = new Timing(timing);
        Task.totalTasks++;
    }

    /**
     * Returns date of the event. Represented
     * as a Date object.
     *
     * @return Date of event.
     */
    public Date getDate() {
        return date;
    }

    /**
     * Returns timing of the event. Represented
     * as a Timing object.
     *
     * @return Timing of event.
     */
    public Timing getTiming() {
        return timing;
    }

    /**
     * Class constructor that specifies description
     * and timing of the event.
     *
     * @param description Description of event.
     * @param date Date of event.
     * @param timing Timing of Event.
     */
    public Event(String description, String date, String timing) throws DukeException {

        super(description);
        this.date = new Date(date);
        this.timing = new Timing(timing);
        Task.totalTasks++;
    }

    /**
     * Returns string representation of current Event, indicating
     * timing and completion status.
     *
     * @return String representation of event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: "
                + date.toString() + ", " + timing.toString() + ")";
    }
}
