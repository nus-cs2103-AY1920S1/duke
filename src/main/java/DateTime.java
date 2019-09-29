import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Represents the date and time for events and deadlines.
 * A <code>DateTime</code> object will be created to represent
 * the date and time of tasks. e.g., <code>event test code /at 14/09/2019 0830</code>
 */

public class DateTime {

    protected String input;
    protected Date date;

    public DateTime (String input){
        this.input = input;
    }

    public void setDateAndTime() throws DukeException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HHmm");

        try {
            date = formatter.parse(input);
        } catch (ParseException e) {
            throw new DukeException("OOPS!!! Invalid time and date format. Please enter in dd/MM/yyyy HHmm format");
        }
    }

    public Date getDateAndTime() {
        return this.date;
    }

    @Override
    public String toString() {
        return this.input;
    }
}
