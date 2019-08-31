import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Deadline extends Task {
    private Date date;

    public Deadline(String desc, String date) {
        super(desc);
        try {
            parseTime(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
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
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by : " + date + ")";
    }
}
