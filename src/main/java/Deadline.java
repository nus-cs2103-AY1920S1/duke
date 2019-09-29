/**
 * Represents a different type of Task called Deadline that holds an additional
 * parameter for date and time.
 * A sub-class of Task.
 */
public class Deadline extends Task {
    protected String by;

    /**
     * Constructor to set the description of deadline and due date and time.
     *
     * @param desc The description of the Deadline
     * @param by   The due date and time of the Deadline
     */
    public Deadline(String desc, String by) {
        super(desc);
        this.by = by;
    }

    /**
     * Constructor with additional parameter to set its completion status.
     *
     * @param desc   The description of the Deadline
     * @param by     The due date and time of the Deadline
     * @param isDone The boolean variable to note if Deadline is completed
     */
    public Deadline(String desc, String by, boolean isDone) {
        super(desc, isDone);
        this.by = by;
    }

    /**
     * Overridden writeFormat method to specify that it is a Deadline
     * when saving the data.
     *
     * @return Format for saving data
     */
    @Override
    public String writeFormat() {
        return "D " + isDone + " " + description + "/" + by;
    }

    /**
     * Overridden toString method to print out Deadline object.
     *
     * @return Printing format of Deadline
     */
    @Override
    public String toString() {
        return "[D]" + super.getTask() + " (by: " + by + ")";
    }
}
