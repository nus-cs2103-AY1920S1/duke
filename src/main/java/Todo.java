public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    public String toSave() {
        return "T | " + (super.isDone ? 1 : 0) + " | " + super.description;
    }

}
