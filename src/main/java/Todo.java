public class Todo extends Task {
    protected Todo(String description) {
        super(description);
    }
    protected Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toSaveFormat() {
        return String.format("T | %s", super.toSaveFormat());
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
