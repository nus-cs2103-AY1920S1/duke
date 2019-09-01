public class Todo extends Task{
    /**
     * Constructor for Todo
     * @param description
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Another constructor for Todo
     * @param description
     * @param isDone boolean value on whether it is done or not
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Gets the String representation of the todo.
     * @return the String representation of the todo
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * @return String representation that would be written into data file
     */
    @Override
    public String toDataString() {
        return "T | " + super.toDataString();
    }
}
