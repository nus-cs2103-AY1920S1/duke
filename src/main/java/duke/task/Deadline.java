package duke.task;

/**
 * Represents a Deadline that contains the description of the deadline and when it is due by.
 */
public class Deadline extends Task {
    protected String time;

    /**
     * Creates the Deadline Task Object.
     * @param description contains the description of the Deadline
     * @param time contains the information of when the deadline is due.
     */
    public Deadline(String description, String time) {
        super(description);
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + time + ")";
    }
}
