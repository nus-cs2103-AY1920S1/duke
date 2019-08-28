public class Event extends Task {
    protected String date;
    protected String time;

    public Event(String desc, String dateTime, boolean isDone) {
        super(desc, isDone);
        date = dateTime;
        time = "";
    }
    
    public Event(String desc, String date, String time) {
        super(desc);
        this.date = date;
        this.time = time;
    }

    public Event(String desc, String date, String time, boolean isDone) {
        super(desc, isDone);
        this.date = date;
        this.time = time;
    }

    @Override
    public String toString() {
        if (time.isEmpty()) {
            return String.format("[D][%s] %s (by: %s)", getStatusIcon(), description, date);
        }
        return String.format("[E][%s] %s (at: %s %s)", getStatusIcon(), description, date, time);
    }
}