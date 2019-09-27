package Tasks;

/**
 * An object of an Event task which is also a child of a Task.
 */
public class Event extends Task {

    protected String at;

    /**
     * Creates an instance of an Event task
     *
     * @param description is the description of the task
     * @param at is the date/time of the task
     */
    public Event(String description, String at) {
        super(description);
        assert(description.length()!=0):"Event description cannot be empty";
        this.at = at;
    }

    /**
     * Getter for the at method
     *
     * @return a String of the at variable
     */
    public String getAt() {
        return this.at;
    }

    /**
     * overrides toString method
     *
     * @return formatted Event object (i.e. [D][O] task1 (by: tonight)
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
