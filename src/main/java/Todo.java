public class Todo extends Task {
    //[T][✓] join sports club

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
