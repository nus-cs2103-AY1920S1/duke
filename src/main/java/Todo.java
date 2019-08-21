public class Todo extends Task {
    /**
     * Creates a new Todo task with the given description.
     * @param description       Task to be completed.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a string containing the type of Task, done status, and
     * description.
     * @return  String describing the Todo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
