import java.time.LocalDateTime;

/**
 * Event methods,extends Task.
 */
public class Event extends Task {

    protected String at;

    protected LocalDateTime time = null;
    /**
     * Sets date and time method.
     */
    public void setDateTime(LocalDateTime time) {
        this.time = time;
    }

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

    @Override
    public String toStringintxt() {
        return "E," + super.toStringintxt() + "," + at;
    }
}
