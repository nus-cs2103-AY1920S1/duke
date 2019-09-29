package q.main;

import com.joestelmach.natty.DateGroup;
import com.joestelmach.natty.Parser;
import q.error.FileLoadError;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * A parser that converts natural language to date and time.
 */
public class DateTimeParser {
    public DateTimeParser() {
    }

    /**
     * Parses a natural language string into a date and time.
     *
     * @param s A natural language string that represents a date.
     * @return A Date that was represented by s.
     */
    public static Date parse(String s) {
        Parser parser = new Parser();
        List<DateGroup> groups = parser.parse(s);
        return groups.get(0).getDates().get(0);
    }

    /**
     * Formats a date as a string in the format dd/MM/yyyy HHmm.
     *
     * @param d A Date to be formatted as a string.
     * @return A string in the format dd/MM/yyyy HHmm.
     */
    public static String format(Date d) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HHmm");
        return formatter.format(d);
    }

    /**
     * Parses a string in the format dd/MM/yyyy HHmm.
     * @param s A string in the format dd/MM/yyyy HHmm.
     * @return A Date.
     * @throws FileLoadError If the string is not in the right format.
     */
    public static Date parseFromFile(String s) throws FileLoadError {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HHmm");
        try {
            Date date = formatter.parse(s);
            return date;
        } catch (ParseException e) {
            throw new FileLoadError();
        }
    }
}
