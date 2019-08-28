import java.text.SimpleDateFormat;
import java.util.Date;

/** Task to represent an event. */
class Event extends Task {

    protected Date date;

    public Event(String name, Date date) {
        super(name);
        this.date = date;
    }

    @Override
    public String toString() {
        String doneStr = this.done ? "✓" : "✗";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return String.format("[E][%s] %s (at: %s)", doneStr, this.name, format.format(this.date));
    }
}