package task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A type of Task that includes a date attribute for the date in which the Task takes place.
 *
 * Also includes two SimpleDateFormatter.
 */
public class Event extends Task {
    private Date date;

    /**
     * Used for parsing the input from the user and for formatting the date for Storage.
     */
    private SimpleDateFormat parser = new SimpleDateFormat("dd/MM/yyyy HHmm");

    /**
     * Used for formatting the date for printing to console.
     */
    private SimpleDateFormat formatter = new SimpleDateFormat("d MMM yyyy, hhmma");


    public Event(String name, String dateString) {
        this.name = name;

        try{
            this.date = parser.parse(dateString);
        } catch (ParseException pE) {
            System.err.println(pE);
        }

        this.isDone = false;
    }

    /**
     * Returns a String representing the Task in a format to be saved into the hard disk's storage file.

     * @return A String representation of this Task formatted for the storage file.
     */
    public String toFile() {
        if(isDone) {
            return "E-1-" + name + "-" + parser.format(date);
        } else {
            return "E-0-" + name + "-" + parser.format(date);
        }
    }

    /**
     * Returns a String representing the Task in a format to be printed into the console.
     *
     * @return A String representation of this Task formatted for printing into the console.
     */
    public String toString() {
        if (isDone) {
            return "[E][✓] " + name + " (at: " + formatter.format(date) + ")";
        } else {
            return "[E][✗] " + name + " (at: " + formatter.format(date) + ")";
        }
    }
}
