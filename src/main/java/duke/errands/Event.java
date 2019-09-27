package duke.errands;

public class Event extends Task {

    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

    @Override
    public String getStatus() {
        String completion;
        if (this.isDone) {
            completion = "1";
        } else {
            completion = "0";
        }

        return "E | " + completion + " | " + this.description + " | " + this.at;
    }
}