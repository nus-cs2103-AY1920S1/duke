/**
 * Represents a Todo task.
 */
public class Todo extends Task {

    /**
     * Creates a Todo with the associated description.
     * @param desc Describes the Todo task.
     */
    public Todo(String desc) {
        super(desc);
    }

    public Todo(String desc, boolean isDone) {
        super(desc, isDone);
    }

    @Override
    public String toString() {
        return String.format("[%s][%s] %s", "T", super.getDoneSymbol(), this.desc);
    }

}
