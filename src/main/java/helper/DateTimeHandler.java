package helper;

import Exception.DukeException;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.HashMap;
import java.util.Map;

public class DateTimeHandler {
    public static Map<Long, String> ordNo;

    static {
        ordNo = new HashMap<>(42);
        ordNo.put(1L, "1st");
        ordNo.put(2L, "2nd");
        ordNo.put(3L, "3rd");
        ordNo.put(21L, "21st");
        ordNo.put(22L, "22nd");
        ordNo.put(23L, "23rd");
        ordNo.put(31L, "31st");
        for (long d = 1; d <= 31; d++) {
            ordNo.putIfAbsent(d, d + "th");
        }
    }

    /**
     * Transforms dd/MM/yyyy HHmm into an ordinal date format.
     * @param s is a string in the form of "d/M/yyyy HHmm".
     * @return {@link String}
     */
    public static String getDateTime(String s) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        LocalDateTime dateTime = LocalDateTime.parse(s, formatter);
        DateTimeFormatter dukeFormatter = new DateTimeFormatterBuilder()
                .appendText(ChronoField.DAY_OF_MONTH, ordNo)
                .appendPattern(" 'of' MMMM yyyy, h:ma")
                .toFormatter();

        String st = dateTime.format(dukeFormatter);
        return st;
    }

    /**
     * Transforms dd/MM/yyyy HHmm-HHmm into an ordinal date format.
     * @param s is a string in the form of "d/M/yyyy HHmm-HHmm".
     * @throws DukeException when an error occurred with a specific message.
     */
    public static DateTimeRangeHelper getDateTimeRange(String s) throws DukeException {
        try {
            String[] stringArr = s.split("-");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
            LocalDateTime dateTime = LocalDateTime.parse(stringArr[0], formatter);
            DateTimeFormatter dukeFormatter = new DateTimeFormatterBuilder()
                    .appendText(ChronoField.DAY_OF_MONTH, ordNo)
                    .appendPattern(" 'of' MMMM yyyy, h")
                    .toFormatter();

            DateTimeFormatter toFormatter = DateTimeFormatter.ofPattern("HHmm");
            LocalTime toTime = LocalTime.parse(stringArr[1], toFormatter);
            DateTimeFormatter toTimeFormatter = DateTimeFormatter.ofPattern("ha");

            String st = dateTime.format(dukeFormatter) + "-" + toTime.format(toTimeFormatter);

            return new DateTimeRangeHelper(dateTime.toLocalTime(), toTime, dateTime.toLocalDate(), st);

        } catch (Exception ex) {
            throw new DukeException("I don't think that was a valid event");
        }

    }

}
