public class Event extends Task {

    protected String dateAndTime;

    public Event(String description, String at) {
        super(description);
        this.dateAndTime = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + dateAndTime + ")";
    }
}
