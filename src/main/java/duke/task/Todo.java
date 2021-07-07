package duke.task;

/**
 * The todo task the user does.
 */
public class Todo extends Task {

    /**
     * Initiates the object.
     *
     * @param description Todo's desciption.
     */
    public Todo(String description) {
        super(description);
        this.type = Type.T;
    }
}
