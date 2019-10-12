public class Todo extends Task {

    /**
     * Represents the Todo item specified by the user.
     * @param description refers to details about the todo task
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Stringified representation of the Todo task.
     * @return String todo
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}