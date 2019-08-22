public class Todo extends Task {

    String time;

    public Todo(int num, String task, String type) {
        super(num, task, type);
    }

    public String toString() {
        return String.format("[T][%s] %s", done ? "✓" : "✗",task);
    }
}
