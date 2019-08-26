public class Event extends Task {
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    public Event(String description, boolean isDone, String at) {
        super(description, isDone);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

    @Override
    public String print() {
        if (this.isDone) {
            return "E @ 1 @ " + this.description + " @ " + this.at;
        } else {
            return "E @ 0 @ " + this.description + " @ " + this.at;
        }
    }
}
