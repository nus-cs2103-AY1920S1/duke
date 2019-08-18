public class Event extends Task {
    protected String extraInfo;
    public Event(String description, String extraInfo) {
        super(description);
        this.extraInfo = extraInfo;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.extraInfo + ")";
    }
}
