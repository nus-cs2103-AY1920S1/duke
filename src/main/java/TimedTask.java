import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Represents a task with a date/time attached to it.
 */
public abstract class TimedTask extends Task {

    Calendar date;
    SimpleDateFormat dateFormat;
    SimpleDateFormat fileDateFormat;

    /**
     * Constructor for the TimedTask object.
     *
     * @param details The description of the task.
     * @param date The Calendar object representing the date attached to the task.
     */
    protected TimedTask(String details, Calendar date) {
        super(details);
        this.date = date;
        String suffix = getDaySuffix(date);
        this.dateFormat = new SimpleDateFormat("d'" + suffix + " of' MMMM yyyy, h:mma");
        DateFormatSymbols symbols = new DateFormatSymbols(Locale.getDefault());
        symbols.setAmPmStrings(new String[] { "am", "pm" });
        this.dateFormat.setDateFormatSymbols(symbols);
        this.fileDateFormat = new SimpleDateFormat("d/M/yyyy kkmm");
    }

    /**
     * Takes the Calendar object and gets the suffix for the day of the month.
     * A day ending with the number 2 should have the suffix "nd" to make the word "2nd".
     *
     * @param date The Calendar object of the TimedTask.
     * @return The suffix of the day in the Calendar.
     */
    private String getDaySuffix(Calendar date) {
        int suffixConstant = date.get(Calendar.DAY_OF_MONTH) % 10;
        return suffixConstant == 1
                ? "st"
                : suffixConstant == 2
                ? "nd"
                : suffixConstant == 3
                ? "rd"
                : "th";
    }

    /**
     * Converts the user inputted date/time to a Calendar object.
     *
     * @param input the user's input of the date and time, specified by DD/MM/YYYY HHMM.
     * @return A Calendar object representing the date and time.
     * @throws IceBearException Thrown when the date or its format is invalid.
     */
    public static Calendar parseDateTime(String input) throws IceBearException {
        // Input Validation
        String[] inputArr = input.split(" ");
        if (inputArr.length != 2) {
            throw new IceBearException("Invalid date/time format!"
                    + "Please follow the format: <DD/MM/YYYY><Space><24hr Time Format>");
        }
        // Date Input Validation
        String date = inputArr[0];
        String dateRegex = "^(\\d{1,2})/(\\d{1,2})/(\\d{4})";
        if (!date.matches(dateRegex)) {
            throw new IceBearException("Invalid date format!"
                    + "Please follow the appropriate Date format: DD/MM/YYYY");
        }
        String[] dateArr = date.split("/");
        int day = Integer.parseInt(dateArr[0]);
        int month = Integer.parseInt(dateArr[1]);
        int year = Integer.parseInt(dateArr[2]);
        if (!isValidDate(day, month, year)) {
            throw new IceBearException("Please input a valid Date!");
        }
        // Time Input Validation
        if (!inputArr[1].matches("\\d{4}")) {
            throw new IceBearException("Please follow the 24h Time format: HHMM");
        }
        int hour = Integer.parseInt(inputArr[1].substring(0, 2));
        int minute = Integer.parseInt(inputArr[1].substring(2, 4));
        if (!isValidTime(hour, minute)) {
            throw new IceBearException("Please input a valid Time!");
        }

        return new Calendar
                .Builder()
                .setDate(year, month - 1, day)
                .setTimeOfDay(hour, minute, 0)
                .build();
    }

    /**
     * Helper function to check if the date provided is valid.
     *
     * @param day The number representing the day of the month.
     * @param month The number representing the month.
     * @param year The number representing the year.
     * @return True if the date provided is an actual date in the calendar.
     */
    private static boolean isValidDate(int day, int month, int year) {

        if (month < 1 || month > 12 || day < 1 || day > 31) {
            return false;
        }
        if (month == 4 || month == 6 || month == 9 || month == 11) {
            if (day == 31) {
                return false;
            }
        } else if (month == 2) {
            if (day > 29) {
                return false;
            }
            if (!((year % 4 == 0 && year % 100 != 0) || year % 400 == 0)) {
                // year is not a leap year
                if (day == 29) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Helper function that checks if the time provided is valid.
     *
     * @param hour The hour given in the 24h format.
     * @param minute The minute as given in the 24h format.
     * @return True if the time provided is valid.
     */
    private static boolean isValidTime(int hour, int minute) {
        return hour >= 0 && hour <= 23 && minute >= 0 && minute < 60;
    }
}
