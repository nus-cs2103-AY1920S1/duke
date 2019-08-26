public class Todo extends Task {
    public Todo(String content, int status, int order) {
        super(content, status, order);
    }

    public String toString() {
        return "[T]" + super.toString();
    }

    public String toFile() {
        return "T," + super.toFile();
    }
}