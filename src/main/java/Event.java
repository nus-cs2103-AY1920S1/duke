import java.util.Date;

/**
 * Represents a type of task
 */
public class Event extends Task {

    protected String at;

    protected Date date;

    public Event(String description, String at) {
        super(description);
        this.at = at;

        this.date = DateClass.stringToDate(at);
    }

    /**
     * Returns user-readable string
     * @return User-readable string
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

    /**
     * Returns formatted string used while saving
     * @return Formatted String
     */
    @Override
    public String saveFormat() {
        return String.format("E | %d | %s | %s", this.isDone ? 1 : 0, this.description, this.at);
    }

}
