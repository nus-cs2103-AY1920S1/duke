import java.text.SimpleDateFormat;
import java.util.Date;

public class Event extends Task {

    protected String when;
    protected boolean isValid;

    /**
     * Constructor for Event task.
     *
     * @param description Event task to be attended.
     * @param when Date of the event.
     */
    public Event(String description, String when) {
        super(description);
        this.when = when;
    }

    /**
     * Method to give the string that is to be
     * added to the list of tasks.
     *
     * @return Returns the string to be loaded into
     * the file and printed out.
     */
    @Override
    public String toString() {
        String date = formatDate(when);
        if (getIsCorrectFormat()) {
            return "[E]" + super.toString() + description + " (at: " + date + ")";
        } else {
            return "Invalid date format!";
        }
    }
}