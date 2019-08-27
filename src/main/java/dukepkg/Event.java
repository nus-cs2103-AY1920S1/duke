package dukepkg;

public class Event extends dukepkg.Task {
    private final String at;
    public static final String type = "E";

    public Event(String task, String at) {
        super(task);
        this.at = at;
    }

    public String getAt() {
        return at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
