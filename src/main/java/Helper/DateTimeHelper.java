package duke.helper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

public class DateTimeHelper {
    static DateTimeFormatter INPUTFORMATTER = new DateTimeFormatterBuilder()
            .appendOptional(DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm"))
            .appendOptional(DateTimeFormatter.ofPattern("yyyy/MM/d HHmm"))
            .appendOptional(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"))
            .appendOptional(DateTimeFormatter.ofPattern("yyyy-MM-d HHmm"))
            .appendOptional(DateTimeFormatter.ofPattern("yyyy.MM.dd HHmm"))
            .appendOptional(DateTimeFormatter.ofPattern("yyyy.MM.d HHmm"))
            .appendOptional(DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"))
            .appendOptional(DateTimeFormatter.ofPattern("d/MM/yyyy HHmm"))
            .appendOptional(DateTimeFormatter.ofPattern("d-MM-yyyy HHmm"))
            .appendOptional(DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"))
            .appendOptional(DateTimeFormatter.ofPattern("dd.MM.yyyy HHmm"))
            .appendOptional(DateTimeFormatter.ofPattern("d.MM.yyyy HHmm"))
            .toFormatter();

    static DateTimeFormatter OUTPUTFORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    public static LocalDateTime formatInput(String inputDateTime) throws DukeException {
        try {
            LocalDateTime ldt = LocalDateTime.parse(inputDateTime, INPUTFORMATTER);
            return ldt;
        } catch (Exception e) {
            throw new DukeException("OOPS! Please make sure that date " +
                    "keyed in is of format Year(yyyy)/Month(mm)/Date(dd) Hour(hh)Minute(mm), eg 2019-09-14 1800");
        }
    }

    public static String formatOutput(LocalDateTime outputDateTime) {
        return outputDateTime.format(OUTPUTFORMATTER);
    }


}
