import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Represents an event-type task which inherits from Task.
 * @author Ang Kai Qi
 * @version 0.1.3
 */
public class Event extends Task {

    protected String on;
    protected Date date;
    protected Date startTime;
    protected Date endTime;

    /**
     * Creates an event task with description and duration.
     * @param description Description of event task.
     * @param on Duration of the task.
     */
    public Event(String description, String on) {
        super(description);
        this.on = on;
        System.out.println(on.length());
        assert (on.length() >= 16 || on.length() <= 20) : "Parser failed to process event correctly.";
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        try {
            this.date = formatter.parse(on.substring(0, 9));
            formatter = new SimpleDateFormat("HHmm");
            this.startTime = formatter.parse(on.substring(11, 14));
            this.endTime = formatter.parse(on.substring(16));
        } catch (ParseException pe) {
            System.out.println("Event: Something serious happened here...");
        }
    }

    /**
     * Returns a string representation of the event object for UI.
     * @return A string representation of the event object for UI.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.on + ")";
    }

    /**
     * Returns a string representation of the event object for Storage.
     * @return A string representation of the event object for Storage.
     */
    @Override
    public String saveString() {
        int done = this.getDone() ? 1 : 0;
        return "E" + super.saveString() + " | " + this.on + "\n";
    }
}
