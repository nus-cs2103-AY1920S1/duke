package formatter;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;

/**
 * TimeFormatter class takes in an input string of a date and outputs it in the proper
 * Date/Time format, specified by the Java Date class
 */

public class TimeFormatter {

    /**
     * Returns the Date in proper date format, after parsing the user input version of a date
     *
     * @param str String input of user specified date
     * @return Date specified by user.
     */

   public static Date convertToDate(String str) throws ParseException {
       SimpleDateFormat myFormatter = new SimpleDateFormat("dd/MM/yyyy HHmm");
           return myFormatter.parse(str);
   }

    /**
     * Returns the String format of a date
     *
     * @param idea input Date
     * @return String format of date for easier reading
     */

   public static String convertToString(Date idea) {
       SimpleDateFormat myFormatter = new SimpleDateFormat("dd/MM/yyyy HHmm");
       return myFormatter.format(idea);

   }

   }



