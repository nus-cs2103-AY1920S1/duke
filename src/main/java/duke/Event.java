package duke;

public class Event extends Task {

    protected String at;

    public Event(String description, String at) {
        this(description, false, at);
    }

    public Event(String description, boolean isDone, String at) {
        super(description, isDone);
        this.at = at;
    }

    public String toSaveFormat() {
        return "E" + "|" + super.toSaveFormat() + "|" + at + '\n';
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
