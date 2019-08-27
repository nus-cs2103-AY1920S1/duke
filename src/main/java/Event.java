public class Event extends Task {
    private String at;

    protected Event(String description, String at) {
        super(description);
        this.at = at;
    }
    protected Event(String description, boolean isDone, String at) {
        super(description, isDone);
        this.at = at;
    }

    @Override
    public String toSaveFormat() {
        return String.format("E | %s | %s", super.toSaveFormat(), this.at);
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), this.at);
    }
}
