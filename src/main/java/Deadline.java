public class Deadline extends Task {

    protected String by;

    /**
     * Constructor for Deadline task.
     *
     * @param description description of the deadline.
     * @param by date and time of the deadline in String format
     *
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String date() {
        return by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}