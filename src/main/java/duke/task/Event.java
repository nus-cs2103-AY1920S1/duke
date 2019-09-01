package duke.task;

/**
 * The event task the user does.
 */
public class Event extends Task {
    protected String time; //The event's time.

    /**
     * Initiates the object.
     *
     * @param description Event's description.
     * @param time Event's time.
     */
    public Event(String description, String time) {
        super(description);
        this.type = Type.E;
        this.time = time;
    }

    /**
     * Turns event to string.
     *
     * @return Event in string.
     */
    @Override
    public String toString() {
        return String.format("%s (at: %s)", super.toString(), time);
    }

    /**
     * Turns event to file format.
     *
     * @return Event in file format.
     */
    @Override
    public String toFile() {
        String doneState = isDone ? "1" : "0";
        return String.format("%s | %s | %s | %s", type, doneState, description, time);
    }
}
