public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        String doneIcon = done ? "✓" : "✗";
        return String.format("[T][%s] %s", doneIcon, description);
    }
}
