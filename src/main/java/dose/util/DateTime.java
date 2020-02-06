package dose.util;

import dose.util.exception.DoseException;
import dose.util.exception.ExceptionType;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
//import dose.util.Ui;

public class DateTime {
    private static SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy HHmm");

    public static Date parseDate(String date) throws DoseException {
        try {
            return dateFormatter.parse(date);
        } catch (ParseException e) {
            // deadline entered in wrong format
            throw new DoseException(ExceptionType.INVALID_DATE);
        }
    }

    public static Date snoozeDate(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, 1);
        date = c.getTime();
        return date;
    }
}
