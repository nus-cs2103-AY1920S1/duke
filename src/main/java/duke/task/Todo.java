package duke.task;

/**
 * Represents a Todo Task that is to be done.
 */
public class Todo extends Task{

    /**
     * Creates a Todo Object
     * @param description contains details of the task to be done.
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
