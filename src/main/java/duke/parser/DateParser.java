package duke.parser;

import duke.exception.InvalidDateTimeException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This is a parser used to parse string into a <code>Date</code> clss object. This is used to save the date
 * information in {@link duke.task.Deadline} and {@link duke.task.Event}.
 */
public class DateParser {

    /**
     * Parses the string into a date.
     * @param dateTime a string representation of the date and time
     * @return a <code>Date</code> class object
     * @throws InvalidDateTimeException if an invalid date and time is entered by the user
     */
    public static Date parse(String dateTime) {
        try {
            return new SimpleDateFormat("dd/MM/yyyy HHmm").parse(dateTime);
        }  catch(ParseException pe) {
            throw new InvalidDateTimeException(dateTime);
        }
    }
}
