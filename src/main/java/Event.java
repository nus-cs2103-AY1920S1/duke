public class Event extends Task {

    String time;

    public Event(int num, String task, String time, String type) {
        super(num, task, type);
        this.time = time;
    }

    @Override
    public String toString() {
        return String.format("[E][%s] %s (at: %s)", done ? "✓" : "✗",task, time);
    }
}
