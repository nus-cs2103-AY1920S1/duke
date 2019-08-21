package DukePkg;

public class Event extends DukePkg.Task {
    protected String at;

    public Event(String task, String at) {
        super(task);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + at + ")";
    }
}
