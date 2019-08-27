public class Event extends Task {
    private String date;
    private String time;

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