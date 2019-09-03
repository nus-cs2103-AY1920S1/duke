import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Contains utility functions for handling input and output time String.
 */
public class DateUtil {

    /**
     * Creates a LocalDateTime object form its String representation.
     *
     * @param s String representation of the LocalDateTime object.
     * @return LocalDateTime object represented by the input String.
     * @throws IllegalTimeFormatException If the input String is not convertible to a LocalDateTime object.
     */
    public static LocalDateTime toTime(String s) throws IllegalTimeFormatException {
        String[] dateTime = s.trim().split(" "); // split date and time
        String[] date = dateTime[0].split("/"); // split date into day, month and year

        String[] temp = new String[5];
        for (int i = 0; i < 5; i++) {
            if (i < date.length) {
                // store date information into a String array [dd, MM, yy, hh, mm] that toTime method understands
                temp[i] = date[i];
            } else {
                // use 00 to replace missing date and time information
                temp[i] = "00";
            }
        }
        date = temp;
        String time;
        if (date[0].length() > 2) {
            // user only inputs time, without date, hence date[0] equals dateTime[0] equals s, the time.
            time = dateTime[0];
            date[0] = "00"; // overwrite wrong dd data in date[0]
            date[3] = time.substring(0, 2); // set hh
            date[4] = time.substring(2); // set mm
        } else if (dateTime.length > 1) {
            // user enters both date and time.
            time = dateTime[1];
            if (time.length() < 3) {
                // time is not entered in the format of hhmm.
                throw new IllegalTimeFormatException("☹ Sorry, I couldn't recognise the time.\n"
                        + "     Enter time in the format of 'hhmm' :D");
            }
            date[3] = time.substring(0, 2); // set hh
            date[4] = time.substring(2); // set mm
        }else {
            // user enters date only
            // do nothing
        } return toLocalDateTime(date);
    }

    private static LocalDateTime toLocalDateTime(String[] times) throws IllegalTimeFormatException{
        try {
            LocalDateTime current = LocalDateTime.now(); // default date is the current date
            int year = times[2].equals("00") ? current.getYear() : Integer.parseInt(times[2]);
            int month = times[1].equals("00") ? current.getMonth().getValue() : Integer.parseInt(times[1]);
            int day = times[0].equals("00") ? current.getDayOfMonth() : Integer.parseInt(times[0]);
            int hour = Integer.parseInt(times[3]); // default time is 1200 AM
            int minute = Integer.parseInt(times[4]);
            return LocalDateTime.of(year, month, day, hour, minute);
        } catch (DateTimeException e) {
            throw new IllegalTimeFormatException(
                    "☹ Sorry, I couldn't recognise the time.\n"
                            + "     Try enter in the format of 'dd/MM/yy hhmm' :D");
        } catch (NumberFormatException nfe) {
            throw new IllegalTimeFormatException(
                    "☹ Sorry, only numbers can be recognised for time.\n"
                            + "     Try enter in the format of 'dd/MM/yy hhmm' :D");
        }
    }

    /**
     * Output the String representation of the date and time stored in the LocalDateTime object in desired format.
     *
     * @param dt LocalDateTime object to be printed.
     * @return Formatted String representation of the date and time stored in the LocalDateTime object.
     */
    public static String printTime(LocalDateTime dt) {
        DateTimeFormatter customFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy_@_hh:mma");
        return dt.format(customFormatter);
    }
}
