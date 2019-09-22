package duke;

/**
 * The ToDo class creates a ToDo task
 * that extends from the Task class.
 */
public class ToDo extends Task {

    private String by;

    /**
     * Constructor for class ToDo.
     *
     * @param description The ToDo task.
     * @param isPriority The priority level of event.
     */
    public ToDo(String description, boolean isPriority) {
        super(description, isPriority);
    }

    /**
     * Overrides the toString method to print the todo task.
     *
     * @return String ToDo task formatted to string.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
