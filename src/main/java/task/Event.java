package task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Specific extension of Task Class.
 */
public class Event extends Task {

    protected Date date;
    protected SimpleDateFormat formatter;


    public Event(String description, String date) throws ParseException {
        super(description);
        formatter = new SimpleDateFormat("dd/M/yyyy hh:mm");
        this.date = formatter.parse(date);
    }

    /**
     * Second constructor for reading data from hard disk storage.
     * @param isDone status of this task.
     * @param description description of this task.
     * @param date event date of this task.
     * @throws ParseException is thrown when user input's date format is incorrect.
     */
    public Event(String isDone, String description, String date) throws ParseException {
        super(description);
        formatter = new SimpleDateFormat("dd/M/yyyy hh:mm");
        this.date = formatter.parse(date);
        if (isDone.equals("\u2713")) {
            this.isDone = true;
        }
    }

    /**
     * Used to store into hard disk storage for easy reading.
     * @return format output for hard disk storage.
     */
    public String toDataBase() {
        return "[E] | " + getStatusIcon() + " | " + description + " | " + formatter.format(date);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + formatter.format(date) + ")";
    }
}