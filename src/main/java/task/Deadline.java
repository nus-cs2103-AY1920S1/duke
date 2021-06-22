package task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Specific extension of Task Class.
 */
public class Deadline extends Task {

    private Date date;
    private SimpleDateFormat formatter;

    /**
     * Constructor for Deadline Task.
     * @param description description of this task.
     * @param date date of this deadline.
     * @throws ParseException is thrown when date format is wrong.
     */
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
        if (isDone.equals("1")) {
            this.isDone = true;
        }
    }

    /**
     * Used to store into hard disk storage for easy reading.
     * @return format output for hard disk storage.
     */
    @Override
    public String toDataBase() {
        return "[D] | " + 1 + " | " + description + " | " + formatter.format(date);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + formatter.format(date) + ")";
    }
}