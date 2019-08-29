/**
 * Represents a task of the type ToDo.
 */
public class ToDo extends Task {

    /**
     * Creates a new instance of ToDo labeled "T".
     *
     * @param s description of the task
     */
    public ToDo(String s) {
        super(s);
        this.label = "T";
    }

    /**
     * Returns a line with details of the ToDo task.
     *
     * @return String representing the task.
     */
    @Override
    public String toString() {
        return "[" + this.label + "]" + this.getStatusIcon() + this.description;
    }
}
