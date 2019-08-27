import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DukeDateTimeFormatter {
    private static final DateTimeFormatter prettyFormat = DateTimeFormatter.ofPattern("d MMM yyyy HH:mm");
    private static final DateTimeFormatter standardFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");

    public static String prettyFormat(LocalDateTime dateTime) {
        return dateTime.format(prettyFormat);
    }
    
    public static String standardFormat(LocalDateTime dateTime) {
        return dateTime.format(standardFormat);
    }
}
