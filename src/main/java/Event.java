/**
 * A sub-class of Task that adds a time/period of time
 * in which the task will take place at/during.
 */
public class Event extends Task {
    protected String at;

    /** Basic constructor for the Event sub-class.
     * Takes in description of the task as well
     * as the time period at which it occurs.
     * @param description details of the task.
     * @param at time period at which task occurs.
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /** Override toString() function for the Event
     * sub-class. Prefixes a "[E]" to indicate the
     * type of task.
     * @return a string representation of the Event object.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
