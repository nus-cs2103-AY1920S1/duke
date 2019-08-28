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
    public String getFileStringFormat() {
        return "E | " + (this.isDone ? "1" : "0") + " | " + this.description + " | " + this.at;
    }

    public String getAt() {
        return at;
    }

    public void setAt(String at) {
        this.at = at;
    }
}
