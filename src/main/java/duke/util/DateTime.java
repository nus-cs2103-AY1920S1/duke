package duke.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
//import duke.util.Ui;

public class DateTime {
    // todo: date format is not implemented correctly
    private static DateFormat dateFormat = new SimpleDateFormat("dd/mm/YYYY HHmm");

    public static Date parseDate(String date) {
        Date d = null;
        try {
            d = dateFormat.parse(date);
        } catch (ParseException e) {
            // how to use ui's methods without passing Ui to this method?
            System.out.println("Oops! You did not enter the date in an appropriate format.\n" +
                    "Try: DD/MM/YYYY HHmm instead.");
        }
        return d;
    }

}
