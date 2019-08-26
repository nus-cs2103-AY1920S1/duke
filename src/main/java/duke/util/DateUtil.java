package duke.util;

import duke.exception.DukeException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    public static SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HHmm");

    public static Date parse(String date) throws DukeException {
        try {
            return formatter.parse(date);
        } catch (ParseException e) {
            throw new DukeException(e.getMessage());
        }
    }

    public static String format(Date date) {
        return formatter.format(date);
    }
}
