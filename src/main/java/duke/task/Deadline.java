package duke.task;

import duke.datetime.Date;
import duke.datetime.Timing;
import duke.dukeexception.DukeException;

/**
 * Represents an actual deadline that the user has to meet.
 */
public class Deadline extends Task {
    private Date date;
    private Timing timing;

    /**
     * Class constructor that specifies description, completion status
     * and timing of the deadline.
     *
     * @param description Description of deadline.
     * @param date Date of deadline.
     * @param timing Timing of deadline.
     * @param done Completion status of deadline.
     */
    public Deadline(String description, String date, String timing, int done) throws DukeException {
        super(description, done);
        this.date = new Date(date);
        this.timing = new Timing(timing);
        Task.totalTasks++;
    }

    /**
     * Returns date of the deadline. Represented
     * as a Date object.
     *
     * @return Date of deadline.
     */
    public Date getDate() {
        return date;
    }


    /**
     * Returns timing of the deadline. Represented
     * as a Timing object.
     *
     * @return Timing of deadline.
     */
    public Timing getTiming() {
        return timing;
    }

    /**
     * Class constructor that specifies description
     * and timing of the deadline.
     *
     * @param description Description of deadline.
     * @param date Date of deadline.
     * @param timing Timing of deadline.
     */
    public Deadline(String description, String date, String timing) throws DukeException {
        super(description);
        this.date = new Date(date);
        this.timing = new Timing(timing);
        Task.totalTasks++;
    }

    /**
     * Returns string representation of current Deadline, indicating
     * timing and completion status.
     *
     * @return String representation of deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + date.toString() + ", " + timing.toString() + ")";
    }
}
