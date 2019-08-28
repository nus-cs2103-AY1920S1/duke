public class Event extends Task {

    protected String at;

    public Event(boolean done, String description, String at) {
        super(description);
        this.at = at;
        this.isDone = done;
    }

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

    public String saveFormat() {
        return String.format("E | %d | %s | %s\n", isDone ? 1 : 0, getDesc(), at);
    }
}
