public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String getSimplifiedRepresentation() {
        return "T | " + (super.isDone ? 1 : 0) + " | " + super.taskName;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
