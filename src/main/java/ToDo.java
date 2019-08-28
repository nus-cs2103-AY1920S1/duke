/**
 * Represents a task that the user wants to do.
 */

public class ToDo extends Task {

    /**
     * Construct a task object that the user wants to do.
     * @param description Description of task
     */
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
