import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Class that converts strings to dates
 */
public class DateClass {

    /**
     * Converts String object to Date object
     * @param string The String
     * @return The Date
     */
    public static Date stringToDate (String string) {

        assert string.isBlank() : "String should not be Blank";

        SimpleDateFormat format = new SimpleDateFormat("d/MM/yyyy HHmm");

        Date date = null;

        try {
            date = format.parse(string);
        } catch (ParseException e) {
            
        }

        return date;
    }
}
