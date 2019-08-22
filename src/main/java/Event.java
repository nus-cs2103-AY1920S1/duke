public class Event extends Task {
    private String time;
    public Event(String s, String t) {
        super(s);
        this.time = t;
    }

    public String toString() {
        return "[E]" + this.getStatusIcon() + this.description + " (at: " + this.time + ")";
    }
}
