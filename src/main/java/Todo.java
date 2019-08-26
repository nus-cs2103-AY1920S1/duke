public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    public Todo(String description, String isDone) {
        super(description.trim(), isDone.trim());
    }

    @Override
    public String getFormatToFile() {
        return String.format("E | %d | %s \n", (isDone ? 1 : 0), description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}