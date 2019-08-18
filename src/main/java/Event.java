public class Event extends Task {
    private String duration;

    public Event(String description, String duration) {
        super(description);
        this.duration = duration;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)",super.toString(),duration);
    }
}
