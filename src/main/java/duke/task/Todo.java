package duke.task;

/**
 * Todo class represents an act a user wants to perform at an unspecified date.
 *
 * @author scwaterbear
 */
public class Todo extends Task {

    /**
     * Class constructor.
     *
     * @param description description of todo.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Class constructor that must set todo status when instantiated.
     *
     * @param description description of todo.
     * @param isDone set status of todo.
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toDataFormat() {
        return "T" + super.toDataFormat();
    }
}
