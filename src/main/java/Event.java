public class Event extends Task {

    Date date;
    Time time;

    public Event(int num, String task, Date date, Time time, String type, boolean done) {
        super(num, task, type, done);
        this.time = time;
        this.date = date;
    }

    public Event(int num, String task, Date date, Time time, String type) {
        super(num, task, type);
        this.time = time;
        this.date = date;
    }

    @Override
    public String toString() {
        return String.format("[E][%s] %s (at: %s %s)", done ? "✓" : "✗",task, date, time);
    }

    @Override
    public String fileFormat() { return String.format("E | %s | %s | %s %s", done ? "1" : "0", task, date, time); }
}
