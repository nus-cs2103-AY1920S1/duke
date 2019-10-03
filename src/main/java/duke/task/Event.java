package duke.task;

import duke.util.DateTime;

/**
 * A sub-class of Task that adds a time/period of time
 * in which the task will take place at/during.
 */
public class Event extends Task {
    protected DateTime at;

    /** Basic constructor for the Event sub-class.
     * Takes in description of the task as well
     * as the time period at which it occurs.
     * @param description details of the task.
     * @param at time period at which task occurs.
     */
    public Event(String description, DateTime at) {
        super(description);
        this.at = at;
    }

    /**
     * Returns a string representation of the Event object and
     * its various details.
     *
     * @return a string representation of the Event object.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
