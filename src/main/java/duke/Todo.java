package duke;

public class Todo extends Task {

    String time;

    public Todo(int num, String task, String type, boolean done) {
        super(num, task, type, done);
    }

    public Todo(int num, String task, String type) {
        super(num, task, type);
    }

    @Override
    public String toString() {
        return String.format("[T][%s] %s", done ? "✓" : "✗",task);
    }

    @Override
    public String fileFormat() { return String.format("T | %s | %s", done ? "1" : "0", task); }
}
