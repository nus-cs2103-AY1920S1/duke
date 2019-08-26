package duke;

/**
 * Represents a Todo object. A <code>Todo</code> object corresponds to
 * a task to be done.
 */
public class Todo extends Task {
    public Todo(String description) {
        super(description);
        this.type = "T";
    }

    /**
     * Returns the string representation of the Todo object.
     * @return String
     */
    @Override
    public String toString() {
        return "[" + this.type + "][" + this.getStatusIcon() + "] " + this.description;
    }

    /**
     * Returns true if two instances of Todo are equal.
     * Otherwise, returns false.
     *
     * @param o  An object.
     * @return boolean.
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof Todo) {
            Todo todo = (Todo) o;
            return this.description.equals(todo.description);
        }
        return false;
    }
}
