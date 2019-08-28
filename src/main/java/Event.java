public class Event extends Task {
    protected String timeAndDate;

    public Event(String description, String timeAndDate) {
        super(description);
        this.timeAndDate = timeAndDate;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + timeAndDate + ")";
    }

    @Override
    public String toSaveString() {
        return "E | " + super.toSaveString() + " | " + timeAndDate;
    }
}
