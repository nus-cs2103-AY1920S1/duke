/**
 * Represents a todo task.
 */
class Todo extends Task{
    /**
     * Create a todo task.
     * @param name description
     */
    Todo(String name) {
        super(name);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[T][" + getState() + "] " + getName();
    }
}
