package duke.time;

import duke.exception.DukeUnknownInputException;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;

import static java.time.format.DateTimeFormatter.RFC_1123_DATE_TIME;

/**
 * Handles date and time formatting using the java.time built-in package.
 */
public class DateTime {
    private OffsetDateTime dateTime;

    private DateTime(int year, Month month, int dayOfMonth, int hour, int minute, int second) {
        this.dateTime = LocalDateTime
                .of(year, month, dayOfMonth, hour, minute, second)
                .atOffset(ZoneOffset.ofHours(0));
    }

    /**
     * Creates a DateTime object from format DD/MM/YYYY TTTT, where time T follows the 24-hour format.
     *
     * @param dateTime the string in specified format.
     * @return the DateTime instance.
     */
    public static DateTime of(String dateTime) {
        String[] dateTimeArgs = dateTime.split(" ");
        // enforced format: 2/12/2019 1800
        if (dateTimeArgs.length != 2
                || dateTimeArgs[0].split("/").length != 3
                || Integer.valueOf(dateTimeArgs[1]) < 0
                || Integer.valueOf(dateTimeArgs[1]) > 2400) {
            throw new DukeUnknownInputException("Incorrect deadline String format passed :(");
        }
        int[] dayMonthYear = Arrays
                .stream(dateTimeArgs[0].split("/"))
                .mapToInt(Integer::parseInt)
                .toArray();

        assert dayMonthYear.length == 3: "Incorrect dayMonthYear array size, should be 3";

        int time = Integer.parseInt(dateTimeArgs[1]);
        return new DateTime(dayMonthYear[2], Month.of(dayMonthYear[1]), dayMonthYear[0], time / 100, time % 100, 0);
    }

    @Override
    public String toString() {
        return dateTime.format(RFC_1123_DATE_TIME);
    }
}