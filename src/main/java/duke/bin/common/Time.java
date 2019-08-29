package duke.bin.common;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Time {
    private final String DATE_PATTERN = "dd MMMM yyyy, hh:mm a";
    private LocalDateTime date;
    private String format;

    public Time(String dateString) throws DukeException {
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
                throw new DukeException("Oops something when wrong with your date input, " +
                        "try following this date pattern: " + DATE_PATTERN);
            }
        }
    }

    @Override
    public String toString() {
        return format;
    }
}
