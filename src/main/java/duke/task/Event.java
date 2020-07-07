package duke.task;

import java.lang.reflect.Array;

/**
 * Represents an event, which is a specific type of task.
 */
public class Event extends Task {
    protected Date atDate;
    protected Time atStartTime;
    protected Time atEndTime;
    protected String exactDuration;

    public Event(String description, String duration) {
        super(description);
        this.exactDuration = duration;
        String[] temp = duration.split(" ");
        atDate = new Date((String) Array.get(temp, 0));
        String[] timeArray = ((String) Array.get(temp, 1)).split("-");
        Time atStartTime = new Time((String) Array.get(timeArray, 0));
        Time atEndTime = new Time((String) Array.get(timeArray, 1));
    }

    /**
     * Returns the event time formatted exactly as keyed in by the user.
     *
     * @return event time formatted exactly as keyed in by user.
     */
    public String getExactDuration() {
        return this.exactDuration;
    }

    /**
     * Returns a string representing the type of task.
     *
     * @return string representing type of task.
     */
    @Override
    public String getTaskType() {
        return "E";
    }

    /**
     * Returns string description of the task.
     *
     * @return string description of task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.atDate + ", " + this.atStartTime + "to" + this.atEndTime +")";
    }
}