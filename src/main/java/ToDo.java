/**
 * Represents of the task-type ToDo.
 */
public class ToDo extends Task {

    /**
     * Creates an instance of ToDo with label 'T'.
     *
     * @param description Stores the description of the task.
     */

    public ToDo(String description) {
        super(description);
        this.wordLabel = "T";
    }

    /**
     * Returns a line with the details of the ToDo task in a String.
     *
     * @return String representation of the task ToDo.
     */
    public String toString() {
        return "[T]" + this.getStatusIcon() + this.description;
    }
}

