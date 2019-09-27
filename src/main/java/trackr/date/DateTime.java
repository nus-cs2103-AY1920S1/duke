package trackr.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This class processes date input from the user. For example, if the command is deadline return book /by
 * 2/12/2019 1800, Duke understands 2/12/2019 1800 as 2nd of December 2019, 6pm, instead of storing it simply
 * as a String.
 */
public class DateTime {

    /**
     * Processed day from user input.
     */
    String day;

    /**
     * Processed month from user input.
     */
    String month;

    /**
     * Processed year from user input.
     */
    String year;

    /**
     * Processed time from user input.
     */
    String time;

    /**
     * Class constructor that assigns user input date and time to the instance.
     * @param dateTime Date and time input form user
     */
    public DateTime(String dateTime) throws ParseException {
        processDate(dateTime);
    }

    /**
     * This method processes the user's date and time input into the desired format.
     * @param dateTime Date and time input by user
     * @throws ParseException When date is not in specified format
     */
    public void processDate(String dateTime) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HHmm");
        simpleDateFormat.setLenient(false);
        Date date = simpleDateFormat.parse(dateTime);
        day = new SimpleDateFormat("d").format(date);
        month = new SimpleDateFormat("MMMM").format(date);
        year = new SimpleDateFormat("yyyy").format(date);
        time = new SimpleDateFormat("h:mma").format(date).toLowerCase();
        processDay(day); // adds st, nd, rd, th suffix to day
    }

    /**
     * Adds suffix to day.
     * @param d Day string without suffix
     */
    public void processDay(String d) {
        int dayNum = Integer.parseInt(d);
        if (dayNum >= 11 && dayNum <= 13) {
            day = d + "th";
        } else if (dayNum % 10 == 1) {
            day = d + "st";
        } else if (dayNum % 10 == 2) {
            day = d + "nd";
        } else if (dayNum % 10 == 3) {
            day = d + "rd";
        } else {
            day = d + "th";
        }
    }

    /**
     * Getter to retrieve the time.
     * @return String Time in desired format
     */
    public String getTime() {
        return time;
    }

    /**
     * Returns date and time in desired format.
     * @return String Formatted date and time
     */
    @Override
    public String toString() {
        String result = day + " of " + month + " " + year + ", " + time;
        return result;
    }
}
