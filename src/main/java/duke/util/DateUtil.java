package duke.util;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class DateUtil {
    /**
     * Parse String from File into Date.
     * @param strDate the date in the form of a string
     * @throws ParseException if it fails to parse the date
     */
    public static Date parseFileStringToDate(String strDate) throws ParseException {
        return new SimpleDateFormat("EEE MMM dd HH:mm:ss yyyy zzz").parse(strDate);
    }

    /**
     * Parse String into Date.
     * @param strDate the date in the form of a String
     * @throws ParseException if it fails to parse the date
     */
    public static Date parseStringToDate(String strDate) throws ParseException {
        return new SimpleDateFormat("dd/mm/yy HHmm").parse(strDate);
    } 
}
