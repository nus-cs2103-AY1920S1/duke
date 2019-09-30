package duke.helper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

public class DateTimeHelper {

    //contains possible formats of inputs that the user may pass in for the date and time.
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

    //formatter for user input to be converted to to standardize.
    static DateTimeFormatter OUTPUTFORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    /**
     * Formats the user input for date and time using the INPUTFORMATTER to ensure it is in the proper format.
     *
     * @param inputDateTime is the date and time passed in by the user.
     * @throws DukeException which occurs if there is problem in user input date and time and outputs error to user.
     */
    public static LocalDateTime formatInput(String inputDateTime) throws DukeException {
        try {
            LocalDateTime ldt = LocalDateTime.parse(inputDateTime, INPUTFORMATTER);
            return ldt;
        } catch (Exception e) {
            throw new DukeException("OOPS! Please make sure that date "
                    + "keyed in is of format Year(yyyy)/Month(mm)/Date(dd) Hour(hh)Minute(mm), eg 2019-09-14 1800");
        }
    }

    /**
     * Formats the user input for date and time using the OUTPUTFORMATTER to be printed or saved in the duke.txt.
     *
     * @param outputDateTime is the LocalDateTime saved in the tasks.
     */
    public static String formatOutput(LocalDateTime outputDateTime) {
        return outputDateTime.format(OUTPUTFORMATTER);
    }


}
