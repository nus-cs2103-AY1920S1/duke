public class Deadline extends Task {

    public Deadline(String description) {
        super(description);
    }

    public String toString() {
        return num + ".[D][" + getStatusIcon() + "] " + super.format_description();
    }

    public String done() {
        return "  [D][✓] " + super.format_description();
    }
}
