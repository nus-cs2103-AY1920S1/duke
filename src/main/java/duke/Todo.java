package duke;

/**
 * Represents a Todo object. A <code>Todo</code> object corresponds to
 * a task to be done.
 */
public class Todo extends Task {
    static final String TODO_TYPE = "T";

    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the string representation of the Todo object.
     * @return String
     */
    @Override
    public String toString() {
        return "[" + TODO_TYPE + "][" + this.getStatusIcon() + "] " + this.description + getTags();
    }

    /**
     * Returns true if two instances of Todo are equal.
     * Otherwise, returns false.
     *
     * @param object  An object.
     * @return boolean.
     */
    @Override
    public boolean equals(Object object) {
        if (object instanceof Todo) {
            Todo todo = (Todo) object;
            return this.description.equals(todo.description);
        }
        return false;
    }
}
