public class Event extends Task {
    private String period;

    Event(String description, String period) {
        super(description);
        this.period = period;
    }

    @Override
    String saveFormat() {
        return "E|" + super.saveFormat();
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + period + ")";
    }
}
