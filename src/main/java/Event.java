public class Event extends Task {

    protected String when;

    public Event(String desription, String when) {
        super(desription);
        this.when = when;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + when + ")";
    }

}