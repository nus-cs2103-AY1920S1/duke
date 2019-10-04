/**
 * Object representing an To Do Task.
 */
public class ToDoTask extends Task {
    public ToDoTask(String details) {
        super(details);
    }

    @Override
    protected String toFileString() {
        int done = isDone ? 1 : 0;
        return "T" + " | " + done + " | " + details;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
