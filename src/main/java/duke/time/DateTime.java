package duke.time;

import duke.exception.DukeUnknownInputException;
import duke.task.TaskType;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

/**
 * Handles date and time formatting using the java.time built-in package.
 */
public class DateTime {
    // code adapted from duke/WeomuCat
    public static final Locale LOCALE = Locale.ENGLISH;
    public static final String DEADLINE_PARSE_PATTERN = "dd/MM/yy HHmm";
    public static final String DEADLINE_FORMAT_PATTERN = "dd MMMM yyyy, hh:mma, O";

    private ZonedDateTime dateTime;

    private DateTime(ZonedDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public static DateTime of(String in, TaskType type) throws DukeUnknownInputException {
        switch (type) {
        case DEADLINE:
        case EVENT:
            return parseDeadline(in);
        default:
            throw new DukeUnknownInputException("Invalid task specified, please pass Events or Deadlines");
        }

    }

    private static DateTime parseDeadline(String by) throws DukeUnknownInputException {
        try {
            DateTimeFormatter parseFormatter = DateTimeFormatter.ofPattern(DEADLINE_PARSE_PATTERN, LOCALE);
            ZonedDateTime dateTime = ZonedDateTime.parse(by, parseFormatter);
            return new DateTime(dateTime);
        } catch (DateTimeParseException e) {
            throw new DukeUnknownInputException(
                    String.format("Invalid date format. Please follow the format %s", DEADLINE_PARSE_PATTERN));
        }
    }

//    private OffsetDateTime dateTime;
//
//    private DateTime(int year, Month month, int dayOfMonth, int hour, int minute, int second) {
//        this.dateTime = LocalDateTime
//                .of(year, month, dayOfMonth, hour, minute, second)
//                .atOffset(ZoneOffset.ofHours(0));
//    }
//
//    /**
//     * Creates a DateTime object from format DD/MM/YYYY TTTT, where time T follows the 24-hour format.
//     *
//     * @param dateTime the string in specified format.
//     * @return the DateTime instance.
//     */
//    public static DateTime of(String dateTime) {
//        String[] dateTimeArgs = dateTime.split(" ");
//        // enforced format: 2/12/2019 1800
//        if (dateTimeArgs.length != 2
//                || dateTimeArgs[0].split("/").length != 3
//                || Integer.valueOf(dateTimeArgs[1]) < 0
//                || Integer.valueOf(dateTimeArgs[1]) > 2400) {
//            throw new DukeUnknownInputException("Incorrect deadline String format passed :(");
//        }
//        int[] dayMonthYear = Arrays
//                .stream(dateTimeArgs[0].split("/"))
//                .mapToInt(Integer::parseInt)
//                .toArray();
//
//        assert dayMonthYear.length == 3 : "Incorrect dayMonthYear array size, should be 3";
//
//        int time = Integer.parseInt(dateTimeArgs[1]);
//        return new DateTime(dayMonthYear[2], Month.of(dayMonthYear[1]), dayMonthYear[0], time / 100, time % 100, 0);
//    }
//
//    @Override
//    public String toString() {
//        return dateTime.format(RFC_1123_DATE_TIME);
//    }
}