package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StringToDate {
    private Date date;

    /**
     * Create a new date time in the required format.
     *
     * @param rawDate date time inputted by user
     * @throws ParseException in case date time is in invalid format
     */
    public StringToDate(String rawDate) throws ParseException {
        String dateFormat = "dd-MM-yyyy HH:mm";
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
        this.date = formatter.parse(rawDate);
    }

    /**
     * Show user the date.
     *
     * @return string representation of the date and time
     */
    @Override
    public String toString() {
        String dateFormat = "dd-MM-yyyy HH:mm";
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
        return formatter.format(this.date);
    }
}
