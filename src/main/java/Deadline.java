import java.util.Date;

/**
 * Represents a type of task
 */
public class Deadline extends Task {

    protected String by;

    protected Date date;

    /**
     * Initialises the Deadline Class with the description of the deadline and when it is due
     * @param description Description of the Deadline
     * @param by When it is due
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;

        this.date = DateClass.stringToDate(by);
    }

    /**
     * Returns user-readable string
     * @return User-readable String
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    /**
     * Returns formatted string used while saving
     * @return Formatted String
     */
    @Override
    public String saveFormat() {
        return String.format("D | %d | %s | %s", this.isDone ? 1 : 0, this.description, this.by);
    }

}
