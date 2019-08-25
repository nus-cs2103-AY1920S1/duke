public class Event extends Task {
    private String eventTime;

    Event(String description, String eventTime) {
        super(description);
        this.eventTime = eventTime;
    }

    @Override
    public String getOutputFormat() {
        return String.format("E | %d | %s | %s", isDone ? 1 : 0, description, eventTime);
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), eventTime);
    }
}
