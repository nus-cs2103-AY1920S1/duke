public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    public String formattedString() {
        return String.format("T | %d | %s", super.isDone ? 1 : 0, super.description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}