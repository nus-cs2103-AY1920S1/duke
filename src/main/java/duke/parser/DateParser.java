package duke.parser;

import duke.exception.DukeException;
import duke.exception.IncorrectFormatException;
import duke.exception.MissingFieldException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

public class DateParser {

    private static final String[] allDays = new String[] {
        "sunday", "monday", "tuesday", "wednesday", "thursday", "friday", "saturday"};
    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HHmm", java.util.Locale.ENGLISH);

    /**
     * Parses any description and determines the type of command that was entered.
     *
     * @param fullDate The command input by user.
     * @return The parsed description.
     * @throws DukeException In the event that a description has an incorrect format/empty fields.
     */
    public static Date[] parse(String fullDate) throws DukeException {
        try {
            if (fullDate.contains("-")) {
                String[] dates = fullDate.split("-");
                return new Date[]{parseDate(dates[0]), parseDate(dates[1])};
            } else {
                return new Date[] {parseDate(fullDate)};
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new MissingFieldException("<$ending_date>");
        }
    }

    /**
     * Parses a date string and reformats it as a date.
     *
     * @return The string representation of the new date object.
     */
    private static Date parseDate(String date) throws DukeException {
        try {
            Date myDate = sdf.parse(parseNaturalDate(date));
            sdf.applyPattern("EEE, d MMM yyyy, hh:mm a");
            String dateString = sdf.format(myDate);
            return sdf.parse(dateString);
        } catch (ParseException e) {
            throw new IncorrectFormatException("Date formats", "should have the format e.g. 11/12/1999 1600");
        }
    }

    /**
     * Parses a natural date string and reformats it as a date.
     *
     * @return The string representation of the new date object.
     */
    private static String parseNaturalDate(String date) {
        Calendar calendar = Calendar.getInstance();
        String day = date.split("\\s+")[1];
        switch (day) {
        case "today":
            return sdf.format(setToEndOfDay(calendar.getTime()));
        case "tomorrow":
            calendar.add(Calendar.DATE, 1);
            return sdf.format(setToEndOfDay(calendar.getTime()));
        default:
            if (Arrays.stream(allDays).parallel().anyMatch(date::contains)) {
                Optional<String> matching = Arrays.stream(allDays).parallel().filter(date::contains).findAny();
                return parseDaysOfWeek(matching, date, calendar);
            }
            return date;
        }
    }

    /**
     * Parses a day in the week to a date object.
     *
     * @param day The day to be parsed.
     * @param calendar The calendar to add days to the date.
     * @return The string representation of the new date object.
     */
    private static String parseDaysOfWeek(Optional<String> day, String date, Calendar calendar) {
        System.out.println(day);
        int dayToday = calendar.get(Calendar.DAY_OF_WEEK);
        int days = Arrays.asList(allDays).indexOf(day.get()) + 1 - dayToday;
        System.out.println(days);
        if (days <= 0) {
            days += 7;
            System.out.println("negative:: " + days);
        }
        if (date.contains("next")) {
            // If the day has not passed this week, e.g. wednesday will assume it to be wednesday of this week.
            days += 7;
            System.out.println("next " + days);
        }
        calendar.add(Calendar.DAY_OF_YEAR, days);
        return sdf.format(setToEndOfDay(calendar.getTime()));
    }

    /**
     * Sets the time of the date to 2359.
     *
     * @param date The date to be edited.
     * @return The new date object with time 2359.
     */
    private static Date setToEndOfDay(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }
}
