import java.time.LocalDateTime;

/**
 * Event type of task.
 */
public class Event extends Task {

    protected LocalDateTime at;

    /**
     * Constructor for Event.
     *
     * @param description representing the task description
     * @param at          representing the location
     */
    public Event(String description, LocalDateTime at) {
        super(description);
        this.at = at;
        this.type = "E";
    }

    /**
     * Constructor for Deadline.
     *
     * @param description representing the task description
     * @param done        representing if the task is done
     * @param at          representing the place
     */
    public Event(String description, String done, LocalDateTime at) {
        super(description, done);
        this.at = at;
        this.type = "E";
    }

    /**
     * Method to return the location.
     *
     * @return A String which represents the location
     */
    public String getAt() {
        return at.format(OUT_DTF);
    }

    /**
     * Override toString method.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at.format(OUT_DTF) + ")";
    }
}