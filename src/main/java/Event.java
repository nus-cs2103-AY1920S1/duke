public class Event extends Task{
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }
    public Event(String description, String at, boolean isDone) {
        super(description, isDone);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]["+ this.getStatusIcon() +"] " + super.toString() + " (at: " + at + ")";
    }

    @Override
    public String toSave() {
        String done = isDone ? "1 | " : "0 | ";
        return "T | " + done + description + " | " + at;
    }
}
