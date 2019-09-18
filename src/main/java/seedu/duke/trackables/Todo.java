package seedu.duke.trackables;

/**
 * Represents a \bTodo, a task that needs to be done.
 */
public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    /**
     * Constructs a new \bTodo using string arguments. Typically used when restoring \bTodo from Storage.
     *
     * @param args String arguments containing Event data from Storage.
     */
    public Todo(String... args) {
        super(args);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String getAsString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("T").append(" | ").append(isDone ? "1" : "0").append(" | ").append(this.description);
        return sb.toString();
    }
}
