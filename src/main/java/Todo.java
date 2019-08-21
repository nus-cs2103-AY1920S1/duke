public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    public Todo(String description, boolean isDone) {
        this(description);
        this.isDone = isDone;
    }
}
