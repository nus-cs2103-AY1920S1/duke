public class Todo extends Task {
    public Todo(String s) {
        super(s);
    }

    /**
     * Formats the task such that it can be outputted in a readable form for the user.
     * @return The description of the task.
     */
    @Override
    public String toString() {
        String mark = (isDone ? "✓" : "✗");
        return String.format("[T][%s] %s", mark, taskDescription);
    }
}
