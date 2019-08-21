public class Deadline extends Task {
    protected String by;

    /**
     * Creates a new Deadline with the given description and due date.
     * @param description       Task to be completed.
     * @param by                Due date for the Deadline.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns a string containing the type of Task, done status, description,
     * and deadline.
     * @return  String describing the Deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
