public class Event extends Task {

    protected String at;

    public Event(String description, String at) {
        super(description, at);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + getTime() + ")";
    }
}