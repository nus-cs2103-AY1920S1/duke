package duke.task;

/**
 * Deadline class represents an act a user wants to perform by a specified date.
 *
 * @author scwaterbear
 */
public class Deadline extends Task {
<<<<<<< HEAD
    private String by;
=======


    protected String by;
>>>>>>> branch-A-JavaDoc

    /**
     * Class constructor.
     *
     * @param description description of deadline.
     * @param by target date and time.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Class constructor that must set deadline status when instantiated.
     *
     * @param description description of deadline.
     * @param by target date and time.
     * @param isDone set status of deadline.
     */
    public Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toDataFormat() {
        return "D" + super.toDataFormat() + " | " + by;
    }
}
