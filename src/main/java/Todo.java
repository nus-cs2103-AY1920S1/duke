public class Todo extends Task {
    public Todo(String s) {
        super(s);
    }

    @Override
    public String toString() {
        String mark = (isDone ? "✓" : "✗");
        return String.format("[T][%s] %s", mark, taskDescription);
    }
}
