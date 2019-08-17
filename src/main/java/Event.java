public class Event extends Task {
    protected String time;

    public Event(String description, String time) {
        super(description);
        this.time = time;
    }

    @Override
    public String toString() {
        String doneIcon = done ? "✓" : "✗";
        return String.format("[E][%s] %s (at: %s)", doneIcon, description, time);
    }
}
