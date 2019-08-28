package duke.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateManager {
    private static SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HHmm");

    public static Date stringToDate(String dateString) throws DukeException {
        try {
            return formatter.parse(dateString);
        } catch (ParseException e) {
            throw new DukeException("Please specify the date in the correct format:\n     DD/MM/YYYY HHMM");
        }
    }

    public static String dateToString(Date date) {
        return formatter.format(date);
    }
}
