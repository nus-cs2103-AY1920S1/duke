package seedu.duke.parser;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class DateParser {

    /** Converts a string into a Date object. If the string is not in the specified format
     * (dd/MM/yyyy HHmm), throws ParseException.
     * @param strDate Represents a string meant to be a date.
     * @return Date object that corresponds to the string.
     */
    public Date understandDate(String strDate) {
        try {
            SimpleDateFormat formatter1 = new SimpleDateFormat("dd/MM/yyyy HHmm");
            return formatter1.parse(strDate);
        } catch (ParseException e) {
            return null;
        }
    }
}
