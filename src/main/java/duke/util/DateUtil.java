package duke.util;

import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;

public class DateUtil {
    /**
     * Parse String from File into Date.
     *
     * @param strDate the date in the form of a string
     * @throws ParseException if it fails to parse the date
     */
    public static Date parseFileStringToDate(String strDate) throws ParseException {
        return new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy").parse(strDate);
    }

    /**
     * Parse String into Date.
     *
     * @param strDate the date in the form of a String
     * @throws ParseException if it fails to parse the date
     */
    public static Date parseStringToDate(String strDate) throws ParseException {
        return new SimpleDateFormat("dd/MM/yy HHmm").parse(strDate);
    } 
}
