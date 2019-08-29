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

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * @return String that would be written into data file
     */
    @Override
    public String toDataString() {
        return "T | " + super.toDataString();
    }
}
