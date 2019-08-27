public class Event extends Task {
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toDataFormat() {
        return "E | " + super.toDataFormat() + " | " + this.at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at:" + this.at + ")";
    }
}
