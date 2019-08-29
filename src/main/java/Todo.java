/**
 * A Task object that contains no date and time information.
 *
 */
public class Todo extends Task {

    /**
     * Constructs an unfinished Todo task.
     *
     * @param description Description of the task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Constructs a Todo task.
     *
     * @param description Description of the task.
     * @param isDone True if the task is finished.
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Marks the task as done.
     *
     * @return New finished task.
     */
    @Override
    public Todo finish() {
        return new Todo(description,true);
    }

    /**
     * ToString method for printing.
     *
     * @return String representation of the deadline object.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
