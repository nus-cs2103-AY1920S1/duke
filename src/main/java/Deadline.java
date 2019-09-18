import java.util.Date;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.text.SimpleDateFormat;

/**
 * Represents a Deadline to be completed.
 */
public class Deadline extends Task {
    private Date date;
    private LocalTime time;

    /**
     * Creates a new Deadline with given description, date and time.
     * @param description The description of Deadline.
     * @param date The date of Deadline.
     * @param time The time of Deadline.
     */
    public Deadline(String description, Date date, LocalTime time) {
        super(description);
        this.date = date;
        this.time = time;
    }

    public String getDateString() {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MMM-yyyy");
        return dateFormatter.format(date);
    }

    public String getTimeString() {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mma");
        return timeFormatter.format(time);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + getDateString()  + " "
                + getTimeString() + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Deadline)) {
            return false;
        }

        Deadline other = (Deadline) obj;
        return this.description.equals(other.description)
                && this.date.equals(other.date)
                && this.time.equals(other.time);
    }
}
