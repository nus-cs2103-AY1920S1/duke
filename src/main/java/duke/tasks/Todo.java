package duke.tasks;

public class Todo extends Task {

    /**
     * Create a Todo instance.
     * @param description information about the todo
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Generate the text representation of the todo task in display format.
     * @return the representation of the todo task in display format
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
