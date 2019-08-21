public class Event extends Task{
    protected String span;

    public Event(String description, String span) {
        super(description);
        this.span = span;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + span + ")";
    }
}
