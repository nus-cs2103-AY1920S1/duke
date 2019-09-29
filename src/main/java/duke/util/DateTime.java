package duke.util;

import duke.util.exception.DukeException;
import duke.util.exception.ExceptionType;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
//import duke.util.Ui;

public class DateTime {
    private static SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy HHmm");

    public static Date parseDate(String date) throws DukeException {
        try {
            return dateFormatter.parse(date);
        } catch (ParseException e) {
            // deadline entered in wrong format
            throw new DukeException(ExceptionType.INVALID_DATE);
        }
    }
}
