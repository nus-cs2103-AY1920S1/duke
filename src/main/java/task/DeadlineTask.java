package task;

import exception.DukeException;
import exception.DukeInvalidTaskDateFormatException;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;

/**
 * Represents a Task which has a deadline.
 */
public class DeadlineTask extends Task {
    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HHmm");
    protected Date time;

    /**
     * Creates a DeadlineTask with a given description and time.
     * @param description Task description.
     * @param time Task deadline date and time in the format of "dd/mm/yyyy hhmm".
     */
    public DeadlineTask(String description, String time) throws DukeException {
        super(description);
        try {
            this.time = format.parse(time);
        } catch (ParseException e) {
             throw new DukeInvalidTaskDateFormatException(time);
        }
    }

    public String toFileString() {
        return "D||" + (this.isDone?"1||":"0||")  + this.description + "||" + format.format(this.time);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by:" + time + ")";
    }

}
