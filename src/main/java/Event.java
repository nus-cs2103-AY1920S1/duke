public class Event extends Task {
    String at;

    public Event(String description, String at) {
        this(description, at, false);
    }

    public Event(String description, String at, boolean isDone) {
        super(description, isDone);
        this.at = at;
    }

    /**
     * Returns time of Event.
     * @return Time of Event.
     */
    public String getTime() {
        return this.at;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), this.at);
    }
}
