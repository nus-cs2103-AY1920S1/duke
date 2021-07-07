package duke.date;

import duke.date.exception.IllegalTimeFormatException;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Contains utility functions for handling input and output time String.
 */
public class DateUtil {

    private static final int DATE_TEMPLATE_SIZE = 5;
    private static final String DEFAULT_TIME = "00";
    private static final String MSG_ILLEGAL_TIME = "> < Sorry, I couldn't recognise the time.\n"
            + "Enter time in the format of 'dd/MM/yyyy hhmm'";
    private static final String DATE_FORMAT_PATTERN = "dd/MM/yyyy, hh:mm a";

    /**
     * Creates a LocalDateTime object form its String representation.
     *
     * @param s String representation of the LocalDateTime object.
     * @return LocalDateTime object represented by the input String.
     * @throws IllegalTimeFormatException If the input String is not convertible to a LocalDateTime object.
     */
    public static LocalDateTime toTime(String s) throws IllegalTimeFormatException {
        String[] formattedTime = formatTimeString(s);
        return toLocalDateTime(formattedTime);
    }

    private static String[] formatTimeString(String s) throws IllegalTimeFormatException {
        // split date and time
        String[] dateTime = s.trim().split(" ");

        if (!s.contains("/")) {
            // user only inputs time "hhmm", without date.
            return formatTimeOnly(dateTime[0]);
        } else {
            // user enters both date and time.
            return formatAll(dateTime);
        }
    }

    private static String[] makeDateTemplate(String[] strings) {
        String[] temp = new String[DATE_TEMPLATE_SIZE];
        for (int i = 0; i < DATE_TEMPLATE_SIZE; i++) {
            if (i >= strings.length) {
                temp[i] = DEFAULT_TIME;
                continue;
            }
            temp[i] = strings[i];
        }
        return temp;
    }

    private static String[] formatTimeOnly(String string) {
        String[] template = new String[DATE_TEMPLATE_SIZE];
        for (int i = 0; i < DATE_TEMPLATE_SIZE; i++) {
            template[i] = DEFAULT_TIME;
        }
        template[DATE_TEMPLATE_SIZE - 2] = string.substring(0, 2); // set hh
        template[DATE_TEMPLATE_SIZE - 1] = string.substring(2); // set mm
        return template;
    }

    private static String[] formatAll(String[] strings) throws IllegalTimeFormatException {
        String[] date = strings[0].split("/");
        String[] template = makeDateTemplate(date);

        if (strings.length == 1) {
            // no time input
            template[DATE_TEMPLATE_SIZE - 2] = DEFAULT_TIME; // set hh
            template[DATE_TEMPLATE_SIZE - 1] = DEFAULT_TIME; // set mm
        } else {
            String time = strings[1];
            if (time.length() < 3) {
                // time is not entered in the format of hhmm.
                throw new IllegalTimeFormatException(MSG_ILLEGAL_TIME);
            }
            template[DATE_TEMPLATE_SIZE - 2] = time.substring(0, 2); // set hh
            template[DATE_TEMPLATE_SIZE - 1] = time.substring(2); // set mm
        }
        return template;
    }

    private static LocalDateTime toLocalDateTime(String[] times) throws IllegalTimeFormatException {
        try {
            LocalDateTime current = LocalDateTime.now(); // default date is the current date
            int year = times[2].equals(DEFAULT_TIME) ? current.getYear() : Integer.parseInt(times[2]);
            int month = times[1].equals(DEFAULT_TIME) ? current.getMonth().getValue() : Integer.parseInt(times[1]);
            int day = times[0].equals(DEFAULT_TIME) ? current.getDayOfMonth() : Integer.parseInt(times[0]);
            int hour = Integer.parseInt(times[3]); // default time is 1200 AM
            int minute = Integer.parseInt(times[4]);
            return LocalDateTime.of(year, month, day, hour, minute);
        } catch (DateTimeException | NumberFormatException e) {
            throw new IllegalTimeFormatException(MSG_ILLEGAL_TIME);
        }
    }

    /**
     * Output the String representation of the date and time stored in the LocalDateTime object in desired format.
     *
     * @param dt LocalDateTime object to be printed.
     * @return Formatted String representation of the date and time stored in the LocalDateTime object.
     */
    public static String printTime(LocalDateTime dt) {
        DateTimeFormatter customFormatter = DateTimeFormatter.ofPattern(DATE_FORMAT_PATTERN);
        return dt.format(customFormatter);
    }
}
