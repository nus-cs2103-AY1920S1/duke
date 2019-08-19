package task;

public class Event extends Task {
    String time;

    /**
     * Constructor.
     */
    public Event(String name, String time) {
        super(name);
        this.time = time;
    }

    @Override
    /**
     * Overrides toString method.
     * @return String
     */
    public String toString() {
        String status;
        if (this.isDone) {
            status = "[✓]";
        } else {
            status = "[✗]";
        }
        return String.format("[E]%s %s (at: %s)", status, this.name, this.time);
    }
}
