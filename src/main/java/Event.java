import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;

/**
 * Represents an "Event" task inputted by the user.
 */
public class Event extends Task {
    private String timeDesc;
    private Date date;

    /**
     * Constructs an Events task object.
     * @param desc description of the task by user
     * @param timeDesc inputted time of the task
     * @throws DukeException if task description or time is not inputted,
     *                       or time is inputted in the wrong format
     */
    public Event(String desc, String timeDesc) throws DukeException {
        super(desc);
        this.timeDesc = timeDesc;

        try {
            SimpleDateFormat deadlineFormatter = new SimpleDateFormat("dd/MM/yyyy hhmm-hhmm");
            this.date = deadlineFormatter.parse(timeDesc);
        } catch (ParseException e) {
            throw new DukeException("Please enter deadline in format dd/mm/yyyy hhmm-hhmm");
        }
    }

    /**
     * Converts the object to its string form to be printed.
     * @return String printing out the Event object's description, time
     *          snd status of completion
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + date + ")";
    }

    /**
     * Converts the Events object to the String form to be saved in file.
     * @return String format of the object
     */
    public String toFileFormat() {
        if (isDone) {
            return "E | [✓] | " + taskDesc + " | " + timeDesc + "\n";
        } else {
            return "E | [✗] | " + taskDesc + " | " + timeDesc + "\n";
        }
    }
}
