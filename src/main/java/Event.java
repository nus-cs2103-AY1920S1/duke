import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;

/**
 * Represents an "Event" task inputted by the user.
 */
public class Event extends Task {
    private String timeDescDate;
    private String timeDescTime;
    protected Date date;

    /**
     * Constructs an Events task object.
     * @param desc description of the task by user
     * @param timeDescDate inputted time of the task
     * @param timeDescTime inputted time of the task
     * @throws DukeException if task description or time is not inputted,
     *                       or time is inputted in the wrong format
     */
    public Event(String type, String desc, String timeDescDate, String timeDescTime) throws DukeException {
        super(type, desc);
        this.timeDescDate = timeDescDate;
        this.timeDescTime = timeDescTime;
        try {
            SimpleDateFormat deadlineFormatter = new SimpleDateFormat("dd/MM/yyyy");
            this.date = deadlineFormatter.parse(timeDescDate);
        } catch (ParseException e) {
            throw new DukeException("Please enter event time in format dd/mm/yyyy hhmm-hhmm hha");
        }
    }

    /**
     * Converts the object to its string form to be printed.
     * @return String printing out the Event object's description, time
     *          snd status of completion
     */
    @Override
    public String toString() {
        return super.toString() + " (at: " + date + " " + timeDescTime + ")";
    }

    /**
     * Converts the Events object to the String form to be saved in file.
     * @return String format of the object
     */
    public String toFileFormat() {
        if (isDone) {
            return type + " | [\u2713] | " + taskDesc + " | " + timeDescDate + " " + timeDescTime + "\n";
        } else {
            return type + " | [\u274C] | " + taskDesc + " | " + timeDescDate + " " + timeDescTime + "\n";
        }
    }

    public Date getDate() {
        return this.date;
    }
}
