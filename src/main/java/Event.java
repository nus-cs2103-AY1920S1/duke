import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Inherits from the Task class and contains information about the Event tasks.
 *
 */
public class Event extends Task {

    private Date onDate;

    /**
     * Creates a event task with description, event time and status (done or not done) .
     *
     * @param description the description of the task.
     * @param on time of the event.
     * @param b status of the event.
     */
    public Event(String description, Date on, boolean b) {
        super(description);
        this.onDate = on;
        this.isDone = b;
    }

    public Event(String description, Date on) {
        super(description);
        this.onDate = on;
    }

    /**
     *Method to display information in the required format.
     *
     * @return the description of the event along with time and prefix E to indicate the nature of the event.
     */
    @Override
    public String toString() {
        DateFormat df = new SimpleDateFormat("E, MMM dd yyyy HH:mm");
        return "[E]" + super.toString() + " (at: " + df.format(onDate) + ")";
    }
}
