package converter;

import java.text.ParseException;
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
     * @throws ParseException if the date is unparesable
     */
    public Date convertStringToDate(String strDate) throws ParseException {
        SimpleDateFormat dateWithTime = new SimpleDateFormat("dd/MM/yyyy HHmm");
        dateWithTime.setLenient(false);
        Date date = dateWithTime.parse(strDate); // this will throw ParseException if unparseable date
        //Ensure that dd/MM/yyyy HHmm format is followed (only check length of each component)
        if (!isValidStrDate(strDate)) {
            throw new ParseException("incorrect date", 0);
        }
        return date;
    }

    /**
     * Convert Date object to String object.
     *
     * @param date Java Date object
     * @return String date in the form dd/MM/yyyy HHmm
     */
    public String convertDateToString(Date date) {
        SimpleDateFormat dateWithTime = new SimpleDateFormat("E dd/MM/yyyy HHmm");
        return dateWithTime.format(date);
    }

    /**
     * Checks if String date has the correct length for each component.
     * E.g dd has length 2, yyyy has length 4
     *
     * @param strDate the date in string
     * @return true if all component has the required length stated by dd/MM/yyyy HHmm else false
     */
    private boolean isValidStrDate(String strDate) {
        String[] dateTime = strDate.split("\\s");
        if (dateTime[1].length() != 4) {
            return false;
        }
        String[] dateDetails = dateTime[0].split("/");
        String dd = dateDetails[0];
        String mm = dateDetails[1];
        String yyyy = dateDetails[2];
        return (dd.length() == 2 && mm.length() == 2 && yyyy.length() == 4);
    }

    /**
     * Convert String object to Date object.
     *
     * @param strDate in the format "EEE MMM dd HH:mm:ss zzz yyyy"
     * @return date object
     * @throws ParseException if the date is unparesable
     */
    public Date convertLongStringToDate(String strDate) throws ParseException {
        SimpleDateFormat dateWithTime = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
        Date date = dateWithTime.parse(strDate);
        return date;
    }
}
