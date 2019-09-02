package converter;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Converts String object to Date object.
 */
public class StringDateConverter {
    /**
     * Convert String object to Date object.
     *
     * @param strDate in the format "dd/MM/yyy HHmm"
     * @return date object
     * @throws Exception if the date is unparesable
     */
    public Date convertStringToDate(String strDate) throws Exception {
        SimpleDateFormat dateWithTime = new SimpleDateFormat("dd/MM/yyy HHmm");
        Date date = dateWithTime.parse(strDate);
        return date;
    }

    /**
     * Convert String object to Date object.
     *
     * @param strDate in the format "EEE MMM dd HH:mm:ss zzz yyyy"
     * @return date object
     * @throws Exception if the date is unparesable
     */
    public Date convertLongStringToDate(String strDate) throws Exception {
        SimpleDateFormat dateWithTime = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
        Date date = dateWithTime.parse(strDate);
        return date;
    }
}
