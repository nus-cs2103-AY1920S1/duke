import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Event extends Task {
    private Date date;

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
        this.date = new SimpleDateFormat("dd/MM/yyyy hhmm").parse(time);
    }

    public String getDueDate() {
        return this.date;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at : " + this.date + ")";
    }

}
