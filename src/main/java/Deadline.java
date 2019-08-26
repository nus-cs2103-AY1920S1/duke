import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;

/**
 * Represents a "Deadline" task inputted by the user.
 */
public class Deadline extends Task{
    protected String timeDesc;
    protected Date date;

    /**
     * Constructs a Deadline task object.
     * @param desc description of the task by user
     * @param timeDesc inputted time of the task
     * @throws DukeException if task description or time is not inputted,
     *                       or time is inputted in the wrong format
     */
    public Deadline(String desc, String timeDesc) throws DukeException {
        super(desc);
        this.timeDesc = timeDesc;

        try {
            SimpleDateFormat deadlineFormatter = new SimpleDateFormat("dd/MM/yyyy hhmm");
            this.date = deadlineFormatter.parse(timeDesc);
        } catch (ParseException e) {
            throw new DukeException("Please enter deadline in format dd/mm/yyyy hhmm");
        }
    }

    /**
     * Converts the object to its string form to be printed.
     * @return String printing out the Deadline object's description, time
     *          snd status of completion
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + date + ")";
    }

    /**
     * Converts the Deadline object to the String form to be saved in file.
     * @return String format of the object
     */
    public String toFileFormat() {
        if(isDone) {
            String format = "D | [✓] | " + taskDesc + " | " + timeDesc + "\n";
            return format;
        } else {
            String format = "D | [✗] | " + taskDesc + " | " + timeDesc + "\n";
            return format;
        }
    }

    /**
     * Retrieves the time description of the object.
     * @return Time description of the object in calendar date format
     */
    public Date getDate() {
        return date;
    }
}
