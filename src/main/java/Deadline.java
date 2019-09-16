public class Deadline extends Task {

    protected String by;

    /**
     * Constructor for Deadline task.
     *
     * @param description Deadline task to be added.
     * @param by Date of the deadline task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Method to give the string that is to be
     * added to the list of tasks.
     *
     * @return Returns the string to be loaded into
     *     the file and printed out.
     */
    @Override
    public String toString() {
        assert by != null;
        return "[D]" + super.toString() + " " + description + " (by: " + by + ")";
    }
}