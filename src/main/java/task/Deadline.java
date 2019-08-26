package task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Specific extension of Task Class.
 */
public class Deadline extends Task {

    protected Date date;
    protected SimpleDateFormat formatter;

    public Deadline(String description, String date) throws ParseException {
        super(description);
        formatter = new SimpleDateFormat("dd/M/yyyy hh:mm");
        this.date = formatter.parse(date);
    }

    /**
     * Second constructor for reading data from hard disk storage.
     * @param isDone status of this task.
     * @param description description of this task.
     * @param date deadline of this task.
     * @throws ParseException is thrown when user input's date format is incorrect.
     */
    public Deadline(String isDone, String description, String date) throws ParseException {
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
        return "[D] | " + getStatusIcon() + " | " + description + " | " + formatter.format(date);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + formatter.format(date) + ")";
    }
}