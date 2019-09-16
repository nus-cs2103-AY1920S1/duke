package util;

import error.datetime.UnknownDateTimeException;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

public class DayParser {
    public static LocalDateTime parse(String day) throws UnknownDateTimeException {
        String lowerCaseDay = day.toLowerCase();

        switch(lowerCaseDay) {

            case "mon":
            case "monday":
                return LocalDateTime.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                        .withHour(0)
                        .withMinute(0);

            case "tue":
            case "tues":
            case "tuesday":
                return LocalDateTime.now().with(TemporalAdjusters.next(DayOfWeek.TUESDAY))
                        .withHour(0)
                        .withMinute(0);

            case "wed":
            case "wednesday":
                return LocalDateTime.now().with(TemporalAdjusters.next(DayOfWeek.WEDNESDAY))
                        .withHour(0)
                        .withMinute(0);

            case "thur":
            case "thurs":
            case "thursday":
                return LocalDateTime.now().with(TemporalAdjusters.next(DayOfWeek.THURSDAY))
                        .withHour(0)
                        .withMinute(0);

            case "fri":
            case "friday":
                return LocalDateTime.now().with(TemporalAdjusters.next(DayOfWeek.FRIDAY))
                        .withHour(0)
                        .withMinute(0);

            case "sat":
            case "saturday":
                return LocalDateTime.now().with(TemporalAdjusters.next(DayOfWeek.SATURDAY))
                        .withHour(0)
                        .withMinute(0);

            case "sun":
            case "sunday":
                return LocalDateTime.now().with(TemporalAdjusters.next(DayOfWeek.SUNDAY))
                        .withHour(0)
                        .withMinute(0);

            default:
                throw new UnknownDateTimeException();
        }
    }
}
