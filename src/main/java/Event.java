public class Event extends Task {
    protected String date;
    protected String time;

    public Event(String desc, String date, String time) {
        super(desc);
        this.date = date;
        this.time = time;
    }

    @Override
    public String toString() {
        return String.format("[E][%s] %s (at: %s %s)", getStatusIcon(), description, date, time);
    }
}