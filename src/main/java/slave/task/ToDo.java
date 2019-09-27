package slave.task;

import slave.elements.Tags;

/**
 * Class to represent a to-do task.
 */
public class ToDo extends Task {

    /**
     * Constructor for to-do task.
     *
     * @param description Description of to-do task.
     * @param id Id of to-do task.
     */
    public ToDo(String description, int id, Tags tags) {
        super(description, id, tags);
        this.type = TaskType.TODO;
    }

    /**
     * Converts to-do task to an appropriate String representation.
     *
     * @return Formatted string of to-do task to be printed.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString() + " (Tags: " + this.tags + ")";
    }
}