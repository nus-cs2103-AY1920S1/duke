public class Event extends Task {
    protected String duration;

    public Event(int id, String description, String duration) {
        super(id, description);
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + duration +")";
    }
}