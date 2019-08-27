public class Event extends Task {

    protected String at;

    public Event(String description, String at) {
        super(description, at);
    }

    public Event(String description, String at, String isDone) {
        super(description, isDone);
        this.at = at.trim();
    }
    @Override
    public String getFormatToFile() {
        return String.format("E | %d | %s | %s \n", (isDone ? 1 : 0), description, at);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + getTime() + ")";
    }
}