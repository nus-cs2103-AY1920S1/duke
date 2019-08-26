package seedu.duke.model;

public class Event extends Task{
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.type = "E";
        this.at = at;
    }

    public Event(String description, String at, int status) {
        super(description, status);
        this.type = "E";
        this.at = at;
    }

    @Override
    public String toTextFileString() {
        return super.toTextFileString() + "," + at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
