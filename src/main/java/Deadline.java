/**
 * Deadline task of Duke.
 */
public class Deadline extends Task {

    private String by;

    /**
     * Deadline constructor with description and by as string.
     * @param description description of tasks
     * @param by deadline date. Has to be in the format "DD/MM/YYYY 24hour"
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public String getBy() {
        return by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}