public class Event extends Task {
    private String datetime;

    Event(String description, String datetime) {
        super(description);
        this.datetime = datetime;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), this.datetime);
    }
}
