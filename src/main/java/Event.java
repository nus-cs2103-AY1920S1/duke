public class Event extends Task {
    private String duration;

    public Event(String description, String duration) {
        super(description);
        this.duration = duration;
    }

    public String toString() {
        String statusIcon = getStatusIcon();
        return "[E][" + statusIcon + "] " + this.description + " (at: " + this.duration + ")";
    }
}
