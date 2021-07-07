import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Event extends Task {
    private Date date;
    private String dateToBeStored;

    public Event(String desc) {
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
     * Getter method to get the date in the form of a date
     * @return the date of the event
     */
    public Date getDueDate() {
        return this.date;
    }

    /**
     * Getter method to get the date in the form of a String
     * @return the date of the event
     */
    public String getDateToBeStored() {
        return this.dateToBeStored;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at : " + this.date + ")";
    }

}
