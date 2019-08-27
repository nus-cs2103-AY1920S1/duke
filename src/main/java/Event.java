public class Event extends Task {

    protected String at;

    public Event(String description, String at) {
        super(description, TaskType.EVENT);
        this.at = at;
    }
    public Event(String description, boolean isDone, String at) {
        super(description, TaskType.EVENT, isDone);
        this.at = at;
    }

    @Override
    public String serialise() {
        return String.format("E | %d | %s | %s\n", isDone ? 1 : 0, description, at);
    }

    @Override
    public String toString() {
        return "[E]" + "[" + super.getStatusIcon() + "]" + super.toString() + " (at: " + at + ")";
    }

}
