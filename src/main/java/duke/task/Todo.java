package duke.task;

/**
 * Represents a Todo task.
 */
public class Todo extends Task {

    /**
     * Creates a Todo with the associated description.
     * @param desc Description of the Todo task.
     */
    public Todo(String desc) {
        super(desc);
    }

    /**
     * Creates a Todo with the associated description.
     * @param desc Description of the Todo task.
     * @param isDone if true, the Todo task is done.
     */
    public Todo(String desc, boolean isDone) {
        super(desc, isDone);
    }

    @Override
    public String toString() {
        return String.format("[%s][%s] %s", "T", super.getDoneSymbol(), this.desc);
    }

}
