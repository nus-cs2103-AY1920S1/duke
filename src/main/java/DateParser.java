import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Class to help with parsing Dates to and from Strings.
 */
public class DateParser {

    private final SimpleDateFormat formatter;

    /**
     * Constructs a DateParser with a given date format.
     * 
     * @param format String that represents the expected format of the Date
     */
    public DateParser(String format) {
        formatter = new SimpleDateFormat(format);
    }

    /**
     * Parses a String into a Date object.
     * 
     * @param str String representation of the Date
     * @return Date parsed from String (if valid format)
     * @throws ParseException If the String cannot be parsed into a Date object according to given format
     */
    public Date parse(String str) throws ParseException {
        return formatter.parse(str);
    }

    /**
     * Formats a Date into a String.
     * 
     * @param d The Date to be formatted
     * @return String representation of the Date, in the desired format
     */
    public String format(Date d) {
        return formatter.format(d);
    }
}
