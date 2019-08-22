public class Event extends Task {
    String timeline;

    public Event (String task, String timeline) {
        super(task);
        this.timeline = timeline;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + timeline + ")";
    }
}