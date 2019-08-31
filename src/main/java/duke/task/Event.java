package duke.task;

import duke.calendar.Date;
import duke.calendar.Time;

/**
 * Represents an event task.
 * An <code>Event</code> object corresponds to a type of <code>Task</code> object with a scheduled date and start time
 * and an optional end date and end time.
 */
public class Event extends Task {
    protected Date startDate;
    protected Time startTime;
    protected Date endDate;
    protected Time endTime;

    /**
     * Constructor for <code>Event</code>.
     * @param description Description for the event task.
     * @param startDate Scheduled start date of event.
     * @param startTime Scheduled start time of event.
     * @param endDate Scheduled end date of event.
     * @param endTime Scheduled end time of event.
     */
    public Event(String description, Date startDate, Time startTime, Date endDate, Time endTime) {
        super(description);
        this.startDate = startDate;
        this.startTime = startTime;
        this.endDate = endDate;
        this.endTime = endTime;
    }

    /**
     * Returns the unprocessed start date of the event.
     * @return Unprocessed start date.
     */
    public String getUnprocessedStartDate() {
        return startDate.getUnprocessedDate();
    }

    /**
     * Returns the unprocessed start time of the event.
     * @return Unprocessed start time.
     */
    public String getUnprocessedStartTime() {
        return startTime.getUnprocessedTime();
    }

    /**
     * Returns the unprocessed end date of the event.
     * @return Unprocessed end date.
     */
    public String getUnprocessedEndDate() {
        return endDate.getUnprocessedDate();
    }

    /**
     * Returns the unprocessed end time of the event.
     * @return Unprocessed end time.
     */
    public String getUnprocessedEndTime() {
        return endTime.getUnprocessedTime();
    }

    /**
     * Returns the type of task that this <code>Event</code> object is.
     * @return Event type.
     */
    public String getType() {
        return "E";
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        output.append("[E]" + super.toString() + " (at: " + startDate.toString()
                + (startTime.toString().equals("") ? "" : ", " + startTime.toString()));
        if (endDate.toString().equals("") && endTime.toString().equals("")) {
            output.append(")");
        } else {
            if (endDate.toString().equals("")) {
                output.append(" to " + endTime.toString() + ")");
            } else if (endTime.toString().equals("")) {
                output.append(" to " + endDate.toString() + ")");
            } else {
                output.append(" to " + endDate.toString() + " " + endTime.toString());
            }
        }
        return output.toString();
    }
}
