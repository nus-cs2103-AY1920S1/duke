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

    public String taskSavedTextFormat() {
        String done = "0";
        if (super.isDone) {
            done = "1";
        }
        return "E | " + done + " | " + super.description + " | " + at;
    }
}
