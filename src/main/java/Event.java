public class Event extends Task {
    private String at;

    public Event(String s, String at) {
        super(s);
        this.at = at;
    }

    @Override
    public String toString() {
        String mark = (isDone ? "✓" : "✗");
        return String.format("[E][%s] %s (at: %s)", mark, taskDescription, at);
    }
}
