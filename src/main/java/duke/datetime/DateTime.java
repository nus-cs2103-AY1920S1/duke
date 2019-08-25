package duke.datetime;

import java.util.HashMap;

import duke.exception.InvalidDateTimeException;

/**
 * DateTime class. Each instance contains the day, month, year, hour, minute of a specific time (12hr clock).
 * Date and Time should be delimited by spaces.
 * Date should be in the form of dd/mm/yyyy.
 * Time should be in the form of 24hr clock.
 */
public class DateTime {
    /** Day. */
    private String day;
    /** Month name. */
    private String month;
    /** Year. */
    private String year;
    /** Hours (12hr clock). */
    private String hours;
    /** Minutes. */
    private String minutes;
    /** AM or PM. */
    private String sign;
    /** Month Mapping from 1-12 to Jan-Dec. */
    private static HashMap<String, String> monthMap = new HashMap<>();

    /** Loads the monthMap when class is loaded. */
    static {
        monthMap.put("1", "January");
        monthMap.put("2", "February");
        monthMap.put("3", "March");
        monthMap.put("4", "April");
        monthMap.put("5", "May");
        monthMap.put("6", "June");
        monthMap.put("7", "July");
        monthMap.put("8", "August");
        monthMap.put("9", "September");
        monthMap.put("10", "October");
        monthMap.put("11", "November");
        monthMap.put("12", "December");
    }

    /**
     * Constructor.
     * @param info Date and Time information, delimited by a space.
     * @throws InvalidDateTimeException Invalid info given either in the wrong format, or incompatible.
     */
    public DateTime(String info) throws InvalidDateTimeException {
        try {
            String[] tokens = info.split("\\s+");
            String date = tokens[0];
            String time = tokens[1];
            parseDate(date);
            parseTime(time);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidDateTimeException("Bad String");
        }
    }

    /**
     * Checks date and sets day, month, year fields.
     * @param date Date string to parse.
     * @throws InvalidDateTimeException Thrown if date is not formatted correctly.
     */
    private void parseDate(String date) throws InvalidDateTimeException {
        try {
            String[] tokens = date.split("/");
            if (tokens.length != 3) {
                throw new InvalidDateTimeException("Invalid Date");
            }
            int d = Integer.parseInt(tokens[0]);
            if (d < 0 || d > 31) {
                throw new InvalidDateTimeException("Invalid Date");
            }
            if (monthMap.get(tokens[1]) == null) {
                throw new InvalidDateTimeException("Invalid Date");
            }
            int y = Integer.parseInt(tokens[2]);
            day = tokens[0];
            month = monthMap.get(tokens[1]);
            year = tokens[2];
        } catch (NumberFormatException e) {
            throw new InvalidDateTimeException("Invalid Date");
        }
    }

    /**
     * Checks the time and sets hours, minutes and sign.
     * @param time Time string to be parsed.
     * @throws InvalidDateTimeException Thrown if time string is not formatted correctly.
     */
    private void parseTime(String time) throws InvalidDateTimeException {
        try {
            int t = Integer.parseInt(time);
            int hrs = t / 100;
            int mins = t % 100;
            boolean isAft = hrs > 12;
            hrs = isAft ? hrs - 12 : hrs;
            sign = isAft ? "pm" : "am";
            hours = String.format("%d", hrs);
            minutes = String.format("%02d", mins);
        } catch (NumberFormatException e) {
            throw new InvalidDateTimeException("Invalid Time");
        }
    }

    /**
     * String representation of DateTime.
     * @return String representation of DateTime.
     */
    @Override
    public String toString() {
        String nth;
        int d = Integer.parseInt(day);
        switch (d) {
        case 1:
            nth = "st";
            break;
        case 2:
            nth = "nd";
            break;
        case 3:
            nth = "rd";
            break;
        default:
            nth = "th";
        }
        return String.format("%s%s of %s %s, %s:%s%s", day, nth, month, year, hours, minutes, sign);
    }
}

