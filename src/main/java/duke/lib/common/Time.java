package duke.lib.common;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Time class to hold value of time with correct display.
 */
public class Time {
    private static final String DATE_PATTERN = "dd MMMM yyyy, hh:mm a";
    private LocalDateTime date;
    private String format;

    /**
     * Constructor of time parsed from the proper string format.
     *
     * @param dateString date format given in string.
     * @throws DukeException will be thrown when string is not in correct format.
     */
    public Time(String dateString) throws DukeException {
        assert !dateString.isEmpty() : "date should not be empty";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_PATTERN);
        try {
            date = LocalDateTime.parse(dateString, formatter);
            format = date.format(formatter);
        } catch (DateTimeParseException d) {
            try {
                String[] stringSplit = dateString.split(" ", 2);
                String[] dateSplit = stringSplit[0].split("\\/");
                date = LocalDateTime.of(Integer.valueOf(dateSplit[2]), Integer.valueOf(dateSplit[1]),
                        Integer.valueOf(dateSplit[0]), Integer.valueOf(stringSplit[1].substring(0, 2)),
                        Integer.valueOf(stringSplit[1].substring(2)));
                format = date.format(formatter);
            } catch (ArrayIndexOutOfBoundsException | StringIndexOutOfBoundsException e) {
                throw new DukeException("Oops something when wrong with your date input, "
                        + "try following this date pattern: " + DATE_PATTERN);
            }
        }
    }

    /**
     * Overrides toString.
     *
     * @return the proper string format of this time object.
     */
    @Override
    public String toString() {
        assert !format.isEmpty();
        return format;
    }
}
