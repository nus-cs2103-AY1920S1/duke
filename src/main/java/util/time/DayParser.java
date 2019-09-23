package util.time;

import error.datetime.UnknownDateTimeException;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

/**
 * Utility class to parse strings representing days into the upcoming LocalDateTime.
 */
public class DayParser {
    /**
     * Parse string representing days into corresponding LocalDateTime.
     * @param day string representing day of the week
     * @return corresponding LocalDateTime
     * @throws UnknownDateTimeException if string is invalid
     */
    public static LocalDateTime parse(String day) throws UnknownDateTimeException {
        String lowerCaseDay = day.toLowerCase();

        switch (lowerCaseDay) {
        case "today":
            return LocalDateTime.now()
                    .withHour(0)
                    .withMinute(0)
                    .withSecond(0)
                    .withNano(0);

        case "tomorrow":
            return LocalDateTime.now()
                    .plus(1, ChronoUnit.DAYS)
                    .withHour(0)
                    .withMinute(0)
                    .withSecond(0);

        case "mon":
        case "monday":
            return LocalDateTime.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                    .withHour(0)
                    .withMinute(0)
                    .withSecond(0)
                    .withNano(0);

        case "tue":
        case "tues":
        case "tuesday":
            return LocalDateTime.now().with(TemporalAdjusters.next(DayOfWeek.TUESDAY))
                    .withHour(0)
                    .withMinute(0)
                    .withSecond(0)
                    .withNano(0);

        case "wed":
        case "wednesday":
            return LocalDateTime.now().with(TemporalAdjusters.next(DayOfWeek.WEDNESDAY))
                    .withHour(0)
                    .withMinute(0)
                    .withSecond(0)
                    .withNano(0);

        case "thur":
        case "thurs":
        case "thursday":
            return LocalDateTime.now().with(TemporalAdjusters.next(DayOfWeek.THURSDAY))
                    .withHour(0)
                    .withMinute(0)
                    .withSecond(0)
                    .withNano(0);
        case "fri":
        case "friday":
            return LocalDateTime.now().with(TemporalAdjusters.next(DayOfWeek.FRIDAY))
                    .withHour(0)
                    .withMinute(0)
                    .withSecond(0)
                    .withNano(0);

        case "sat":
        case "saturday":
            return LocalDateTime.now().with(TemporalAdjusters.next(DayOfWeek.SATURDAY))
                    .withHour(0)
                    .withMinute(0)
                    .withSecond(0)
                    .withNano(0);

        case "sun":
        case "sunday":
            return LocalDateTime.now().with(TemporalAdjusters.next(DayOfWeek.SUNDAY))
                    .withHour(0)
                    .withMinute(0)
                    .withSecond(0)
                    .withNano(0);

        default:
            throw new UnknownDateTimeException();
        }
    }
}
