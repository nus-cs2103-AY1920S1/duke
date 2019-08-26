public class Todo extends Task {
    public Todo(String content, int status) {
        super(content, status);
    }

    public String toString() {
        return "[T]" + super.toString();
    }

    public String toFile() {
        return "T," + super.toFile();
    }
}