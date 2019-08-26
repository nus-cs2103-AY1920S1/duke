public class Deadline extends Task {

    private final String by;

    /**
     * Initialises a Deadline task.
     *
     * @param description The deadline description.
     * @param by          The time at which the deadline is up.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * The string representation of a deadline for printing.
     *
     * @return The deadline string for printing.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    /**
     * The string representation of a deadline for saving to file.
     *
     * @return The deadline string for storage.
     */
    @Override
    public String toStore() {
        return "D" + super.toStore() + "|" + by;
    }
}