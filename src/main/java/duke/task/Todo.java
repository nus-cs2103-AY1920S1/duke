package duke.task;

public class Todo extends Task{
    public Todo(String description, boolean isDone, String info) {
        super(description, isDone, info);
        super.type = Type.TODO;
    }

    @Override
    public String toString() {
        return "[T][" + getStatusIcon() + "] " + description;
    }

    @Override
    public String getFileStringFormat() {
        if (isDone()) {
            return "T | 1 | " + description;
        } else {
            return "T | 0 | " + description;
        }
    }
}
