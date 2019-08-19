public class Event extends Task {

    public Event(String description) {
        super(description);
    }

    public String toString() {
        return num + ".[E][" + getStatusIcon() + "] " + format_description();
    }

    public String done() {
        return "  [E][✓] " + format_description();
    }
}
