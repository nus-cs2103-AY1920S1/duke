package task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A type of Task that includes a date attribute for the Task to be completed by. Also includes two SimpleDateFormatter.
 */
public class Deadline extends Task {
    private Date date;

    /**
     * Used for parsing the input from the user and for formatting the date for Storage.
     */
    private SimpleDateFormat parser = new SimpleDateFormat("dd/MM/yyyy HHmm");

    /**
     * Used for formatting the date for printing to console.
     */
    private SimpleDateFormat formatter = new SimpleDateFormat("d MMM yyyy, hhmma");


    /**
     * Constructor for a Deadline Task.
     *
     * @param name A String which represents the name of the Task.
     * @param dateString A String which represents the date and time of the Task.
     */
    public Deadline(String name, String dateString) {
        this.name = name;

        try {
            Date dateTime = parser.parse(dateString);
            this.date = dateTime;
        } catch (ParseException parseExp) {
            System.err.println(parseExp);
        }

        this.isDone = false;
    }

    /**
     * Returns a String representing the Task in a format to be saved into the hard disk's storage file.

     * @return A String representation of this Task formatted for the storage file.
     */
    public String toFile() {
        if (isDone) {
            return "D-1-" + name + "-" + parser.format(date);
        } else {
            return "D-0-" + name + "-" + parser.format(date);
        }
    }

    /**
     * Returns a String representing the Task in a format to be printed into the console.
     *
     * @return A String representation of this Task formatted for printing into the console.
     */
    public String toString() {
        if (isDone) {
            return "[D][\u2713] " + name + " (by: " + formatter.format(date) + ")";
        } else {
            return "[D][\u2717] " + name + " (by: " + formatter.format(date) + ")";
        }
    }
}
