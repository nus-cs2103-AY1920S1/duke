public class Event extends Task {

    protected String on;

    public Event(String description, String on) {
        super(description);
        this.on = on.substring(3);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + on + ")";
    }

    @Override
    public String saveString() {
        int done = this.getDone() ? 1 : 0;
        return "E" + super.saveString() + " | " + this.on + "\n";
    }
}
