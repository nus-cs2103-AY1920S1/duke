import java.text.SimpleDateFormat;
import java.util.Date;

/** Task to represent an event. */
class Event extends Task {

    /**
     * Constructor for the event.
     * @param name Description of event.
     * @param date Date of event.
     */
    public Event(String name, Date date) {
        super(name);
        this.date = date;
        this.type = TaskType.EVENT;
    }

    /**
     * Get the date of event.
     * @return Date of event.
     */
    public Date getDate() {
        return this.date;
    }

    /**
     * String method to override the toString() in java.lang.Object.
     * @return String representation of the object.
     */
    @Override
    public String toString() {
        String doneStr = this.done ? Unicode.TICK : Unicode.CROSS;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return String.format("[E][%s] %s (at: %s)", doneStr, this.name, format.format(this.date));
    }
}