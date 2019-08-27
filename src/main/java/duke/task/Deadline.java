package duke.task;

/**
 * The class is used to represent the task of deadline type.
 */
public class Deadline extends Task {

    private String by;

    /**
     * The constructor of class, assigned the String value to variables.
     *
     * @param description The description of the task.
     * @param by          The date and time of deadline.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "D " + super.toString() + " - " + by;
    }
}