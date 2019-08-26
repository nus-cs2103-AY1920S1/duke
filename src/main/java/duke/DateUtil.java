package duke;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    public static Date parseDate(String rawDate) throws DukeException {
        try {
            Date newDate = new SimpleDateFormat("dd/MM/yyyy HHmm").parse(rawDate);
            return newDate;
        } catch (ParseException e) {
            throw new DukeException("Cannot parse date/time.");
        }
    }

    public static String formatDate(Date date) {
        return new SimpleDateFormat("dd/MM/yyyy HHmm").format(date);
    }
}
