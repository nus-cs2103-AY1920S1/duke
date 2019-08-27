/**
 * The Event class represents a type of task.
 */
public class Event extends Task {

    protected String when;

    /**
     * Creates a Task of type Event.
     *
     * @param desription A string representing the task's description.
     * @param when A string representing time of the task.
     */
    public Event(String desription, String when) {
        super(desription);
        this.when = when;
    }
    /**
     * Gets the task description, time and status.
     *
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + when + ")";
    }

}