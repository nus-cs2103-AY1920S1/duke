package tasks;

/**
 * Encapsulates a todo-type task handled by Duke.
 *
 * <p> A todo differs from other tasks because it has no due date, it simply has
 * a task description. </p>
 *
 * @author atharvjoshi
 * @version CS2103 AY19/20 Sem 1 iP Week 4
 */
public class Todo extends Task {

    /** Tag identifying Todo. */
    private static final String TODO_TAG = "T-";

    /**
     * Create a todo with a description.
     *
     * @param description string representing description of this todo.
     * @param isDone flag indicating whether task has been done or not
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Return a string representation of this todo.
     *
     * @return string representing this todo.
     */
    public String toString() {
        return TODO_TAG + super.toString();
    }
}