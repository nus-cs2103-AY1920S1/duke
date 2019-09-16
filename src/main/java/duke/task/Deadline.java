package duke.task;

/**
 * Represents a type of task known as Deadline.
 */
public class Deadline extends Task {

    // the Deadline subclass adds one field
    public String by;

    /**
     * Initialises a Deadline.
     * A Deadline consists of the task itself and a deadline to be met.
     *
     * @param item name of the task.
     * @param by deadline to be met.
     */
    // the Deadline subclass has one constructor
    public Deadline(String item, String by) {
        super(item);
        this.by = by;
    }

    /**
     * Prints a Deadline as a string to the user in a specific format.
     *
     * @return Deadline.
     */
    @Override
    // overrides toString method in Task
    public String toString() {
        if (isDone) {
            return "[D][" + "/" + "] " + super.toString() + "(by:" + by + ")";
        } else {
            return "[D][" + "x" + "] " + super.toString() + "(by:" + by + ")";
        }
    }

    /**
     * Saves a Deadline as a string to the file in a specific format.
     *
     * @return Deadline.
     */
    @Override
    // overrides saveTask method in Task
    public String saveTask() {
        if (isDone) {
            return "D" + " | " + "1" + " | " + super.toString() + " | " + by + "\n";
        } else {
            return "D" + " | " + "0" + " | " + super.toString() + " | " + by + "\n";
        }
    }

}
