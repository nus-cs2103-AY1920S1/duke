import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;

/**
 * A class wrapper around java.util.Date and java.text.SimpleDataFormat
 * to enable Duke to freely parse input strings and switch between formats
 * for standardized screen output.
 */
public class DateTime implements Serializable {
    private Date dateTime;

    /**
     * Returns a DateTime object whose parameters are initialized by parsing through
     * an input String with format "dd/MM/yy HHmm". Parsing is done through
     * java.text.SimpleDateFormat. Upon success, returns a DateTime
     * object. More templates will be added in the future to increase
     * the versatility of the parsing.
     *
     * @param input String to be parsed into date and time
     * @return java.util.Date object
     * @throws ParseException
     */
    static public DateTime parseString(String input) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HHmm");
        Date dateTime;
        dateTime = format.parse(input);
        return new DateTime(dateTime);
    }

    private DateTime (Date dateTime) {
        this.dateTime = dateTime;
    }

    /**
     * Overridden toString() method for this object.
     * Returns the date and time in a standardized format
     * of "ddth MMM yyyy", eg. 17th FEB 1996
     * @return string representation of DateTime object
     */
    @Override
    public String toString() {
        SimpleDateFormat format = new SimpleDateFormat("dd MMM yyyy, hh:mm a");
        return format.format(this.dateTime);
    }
}
