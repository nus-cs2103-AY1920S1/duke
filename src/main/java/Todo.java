/**
 * Another type of task, todo.
 */
public class Todo extends Task {

    /**
     * Constructor for Todo.
     *
     * @param description of the task
     */
    public Todo(String description) {
        super(description);
        this.type = "T";
    }

    /**
     * Constructor for Todo.
     *
     * @param description of the task
     * @param done        representing if the task is done
     */
    public Todo(String description, String done) {
        super(description, done);
        this.type = "T";
    }

    /**
     * Override the toString method.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}