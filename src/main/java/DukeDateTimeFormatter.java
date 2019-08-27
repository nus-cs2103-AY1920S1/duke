import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DukeDateTimeFormatter {
    private static final DateTimeFormatter prettyFormat = DateTimeFormatter.ofPattern("d MMM yyyy HH:mm");

    public static String format(LocalDateTime dateTime) {
        return dateTime.format(prettyFormat);
    }
}
