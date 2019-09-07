import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Represents a date time handler that changes string to Date and LocalTime.
 */
public class DateTimeHandler {
    private String[] dateTime;
    private Date date;
    private LocalTime time;

    DateTimeHandler(String[] dateTime) {
        this.dateTime = dateTime;
    }

    /**
     * Parses string from user input to Date and LocalTime.
     * @throws ParseException If parsing of date fails.
     */
    public void parseDateTimeFromUserInput() throws ParseException {
        SimpleDateFormat parser = new SimpleDateFormat("dd/MM/yyyy");
        date = parser.parse(dateTime[1]);

        int intTime = Integer.parseInt(dateTime[2]);
        int hour = intTime / 100;
        int minute = intTime % 100;
        time = LocalTime.of(hour, minute);
    }

    /**
     * Parses text from storage to Date and LocalTime.
     * @throws ParseException If parsing of date fails.
     */
    public void parseDateTimeFromStorage() throws ParseException{
        SimpleDateFormat parser = new SimpleDateFormat("dd-MMM-yyyy");
        date = parser.parse(dateTime[1]);

        String strTime = dateTime[2].substring(0, 7);
        time = LocalTime.parse(strTime, DateTimeFormatter.ofPattern("hh:mma"));
    }

    public Date getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }
}
