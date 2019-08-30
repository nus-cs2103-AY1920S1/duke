package duke;

public class Todo extends Task {

    public Todo(String description) {
        this(description, false);
    }

    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    public String toSaveFormat() {
        return "T" + "|" + super.toSaveFormat() + '\n';
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
