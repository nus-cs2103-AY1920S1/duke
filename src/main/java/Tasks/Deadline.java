package Tasks;

/**
 * An object of a Deadline task which is also a child of a Task.
 */
public class Deadline extends Task {

    protected String by;

    /**
     * Creates an instance of a Deadline task
     *
     * @param description is the description of the task
     * @param by is the date/time of the task
     */
    public Deadline(String description, String by) {
        super(description);
        assert(description.length()!=0):"Deadline description cannot be empty";
        this.by = by;
    }

    /**
     * Getter for the by method
     *
     * @return a String of the by variable
     */
    public String getBy() {
        return this.by;
    }

    /**
     * overrides toString method
     *
     * @return formatted Deadline object (i.e. [D][O] task1 (by: tonight)
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}