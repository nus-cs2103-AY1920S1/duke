package DukePkg;

public class Event extends DukePkg.Task {
    protected String at;
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
        return "[E]" + super.toString() + "(at: " + at + ")";
    }
}
