package duke;

import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;

/**
 * Represents a Deadline object. A <code>Deadline</code> object corresponds to
 * an agenda to be done by.
 */
public class Deadline extends Task {
    private Date date;
    private String description;

    public Deadline(String description) {
        super(description);
        this.type = "D";
    }

    /**
     * Sets the date of the deadline in a simple date format.
     * If unable to parse the date, exception is thrown.
     *
     * @param time  String containing date and time of deadline.
     * @throws ParseException  If unable to parse date.
     */
    public void parseTime(String time) throws ParseException {
        this.date = new SimpleDateFormat("dd/MM/yyyy hhmm").parse(time);
    }

    /**
     * Returns the string representation of the Deadline object.
     * @return String
     */
    @Override
    public String toString() {
        return "[" + this.type + "][" + this.getStatusIcon() + "] " + this.description + this.date;
    }

    /**
     * Returns true if two instances of Deadline are equal.
     * Otherwise, returns false.
     *
     * @param o  An object.
     * @return boolean.
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof Deadline) {
            Deadline deadline = (Deadline) o;
            return deadline.toString().equals(this.toString());
        }
        return false;
    }
}
