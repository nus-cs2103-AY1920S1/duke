public class Event extends Task {
    private StringToDate time;

    public Event(String name, StringToDate time) {
        super(name);
        this.time = time;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.time.toString() + ")";
    }
}