public class Event extends Task {

    public Event(String s, String t) {
        super(s, t);
        this.label = "E";
    }

    @Override
    public String toString() {
        return "[" + this.label + "]" + this.getStatusIcon() + this.description + " (at: " + this.time + ")";
    }

}
