/**
 * Todo methods,extends Task.
 */
public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toStringintxt() {
        return "T," + super.toStringintxt();
    }
}
