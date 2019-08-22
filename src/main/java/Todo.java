/**
 * Encapsulates a todo-type task handled by Duke.
 *
 * A todo differs from other tasks because it has no due date, it simply has
 * a task description.
 *
 * @author atharvjoshi
 * @version CS2103 AY19/20 Sem 1 iP Week 2
 */
public class Todo extends Task {

    /**
     * Create a todo with a description.
     *
     * @param description string representing description of this todo.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Return a string representation of this todo.
     *
     * @return string representing this todo.
     */
    public String toString() {
        return "[T]" + super.toString();
    }
}