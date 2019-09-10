public class Event extends Task {

    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
        this.event = "event";
        this.type = "E";
    }

    @Override
    public String getType() {
        return this.type;
    }

    @Override
    public String getAt() {
        return this.at;
    }

    @Override
    public String toString() {
        return "  [E]" + super.toString() + "(at: " + at + ")";
    }
}
