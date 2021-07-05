package duke.task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The Deadline class defines the requirement of a deadline.
 * 
 * @author Joel Loong
 */
public class Deadline extends Task {

    protected String by;
    protected Date date;
    protected final SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");

    /**
     * Use this constructor when reading input from user.
     * 
     * @param description Description of deadline.
     * @param by          Date of deadline.
     */
    public Deadline(String description, String by, int priority) {
        super(description, priority);
        this.by = by;
        this.date = formatDate();
    }

    /**
     * Use this constructor when reading input from data file.
     * 
     * @param description Description of deadline.
     * @param by          Date of deadline.
     * @param isDone      If deadline is completed/over.
     */
    public Deadline(String description, String by, int isDone, int priority) {
        super(description, isDone, priority);
        this.by = by;
        this.date = formatDate();
    }

    private Date formatDate() {
        String[] bySplit = by.split("/");
        String day = bySplit[0];
        String month = bySplit[1];
        String year = bySplit[2];
        if (day.length() == 1) {
            day = "0" + day;
        }
        if (month.length() == 1) {
            month = "0" + month;
        }

        Date date = new Date();
        String dateString = day + "-" + month + "-" + year;
        try {
            date = format.parse(dateString);
        } catch (ParseException e) {
            System.err.println(e.getMessage());
        }

        return date;
    }

    public String getTime() {
        return by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + format.format(date) + ") " + super.priority;
    }
}