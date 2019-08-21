public class Event extends Task{
    protected String span;

    public Event(String description, String span) {
        super(description);
        this.span = span;
    }

    public Event(String description, String span, boolean done) {
        super(description, done);
        this.span = span;
    }

    @Override
    public Event finish() {
        return new Event(description, span,true);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + span + ")";
    }
}
