public class Todo extends Task {
    Todo(String description) {
        super(description);
    }

    Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String dataFormat() {
        return "T" + super.dataFormat();
    }
}
