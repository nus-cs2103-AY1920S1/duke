public class Event extends Task {
    protected String period;

    public Event(String description, String period) {
        super(description);
        this.period = period;
    }

    public String getPeriod() {
        return period;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + period + ")";
    }
}
