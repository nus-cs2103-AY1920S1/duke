package duke.task;

public class Todo extends Task {
    public Todo(final String description, final boolean isDone) {
        super(description, isDone);
    }

    public Todo(final String description) {
        this(description, false);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toStorageString() {
        String[] tokens = new String[]{
            "T",
            this.isDone() ? "1" : "0",
            this.getDescription()
        };
        return String.join(Todo.storageStringSeparator, tokens);
    }
}
