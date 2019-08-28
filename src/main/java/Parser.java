import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {
    private static final DateTimeFormatter standardFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");

    public static LocalDateTime parse(String dateTimeString) throws DukeException {
        try {
            return LocalDateTime.parse(dateTimeString, standardFormat);
        } catch (DateTimeParseException e) {
            throw new DukeException("☹ OOPS! Date Time format invalid!");
        }
    }
}
