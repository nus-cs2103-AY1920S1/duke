package task;

/**
 * Represents the task todo.
 */
public class ToDos extends Task {

    /**
     * Creates the ToDos object.
     *
     * @param description Task message.
     * @param isDone      Marks the task as done nor not done.
     */
    public ToDos(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toString() {
        return "[T]" + "[" + getStatusIcon() + "] " + getDescription();
    }
}
