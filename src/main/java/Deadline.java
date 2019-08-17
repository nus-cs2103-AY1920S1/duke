public class Deadline extends Task {
    protected String deadline;

    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        String doneIcon = done ? "✓" : "✗";
        return String.format("[D][%s] %s (by: %s)", doneIcon, description, deadline);
    }
}
