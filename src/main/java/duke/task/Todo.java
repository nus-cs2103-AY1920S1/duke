package duke.task;

/**
 * Class that represents a todo task.
 */
public class Todo extends Task {

    /**
     * Constructor that takes in the main message of todo task.
     * @param description The description of the todo task.
     */
    public Todo(String description) {
        super(description);
        this.type = "todo";
    }

    @Override
    public String toString() {
        return "[ T ]" + super.toString();
    }
}
