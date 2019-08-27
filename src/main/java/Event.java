import java.text.SimpleDateFormat;
import java.util.Date;

class Event extends Task {
    Date date;

    Event(String task, Date date) {
        super(task);
        this.date = date;
    }

    Event(String task, Boolean done, Date date) {
        super(task, done);
        this.date = date;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss");
        String iconForDone = done ? "\u2713" : "\u2718";
        return String.format("[E][%s] %s (at: %s)", iconForDone, this.task, sdf.format(this.date));
    }
}