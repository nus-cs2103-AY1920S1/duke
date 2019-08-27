public class Todo extends Task {
    protected Todo(String description) {
        super(description);
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
