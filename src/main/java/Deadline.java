import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Deadline extends Task {
    private Date date;
    private String dateToBeStored;

    /**
     * Constructor for deadline
     * Calls parent class Task
     * @param desc
     */
    public Deadline(String desc) {
        super(desc);
    }

    /**
     * Reading the date as entered by the user.
     * Throws ParseException if the format is not met.
     * @param time of the event in String
     * @throws ParseException when unable to Parse the date
     */

    public void parseTime(String time) throws ParseException {
        this.dateToBeStored = time;
        this.date = new SimpleDateFormat("dd/MM/yyyy hhmm").parse(time);
    }

    /**
     * Getter method to get the due date in the form of a date
     * @return the duedate of the deadline
     */

    public Date getDueDate() {
        return this.date;
    }

    /**
     * Getter method to get the due date in the form of a String
     * @return the duedate of the deadline
     */

    public String getDateToBeStored() {
        return this.dateToBeStored;
    }

    /**
     * A representation of the deadline
     * @return a string representing the deadline
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by : " + this.date + ")";
    }
}
