package duke.task;

/**
 * Represents a task of the type Todo.
 */
public class Todo extends Task {

    /**
     * Creates a new instance of <code>Todo</code> labeled "T".
     *
     * @param s description of the task
     */
    public Todo(String s) {
        super(s);
        this.label = "T";
    }

    /**
     * Returns a line with details of the <code>Todo</code> task.
     *
     * @return String representing the task.
     */
    @Override
    public String toString() {
        return "[" + this.label + "]" + this.getStatusIcon() + this.description;
    }
}
