package duke.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Formatter {
    private static final DateTimeFormatter PRETTY_FORMAT = DateTimeFormatter.ofPattern("d MMM yyyy HH:mm");
    private static final DateTimeFormatter STANDARD_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    
    public static String prettyFormatDateTime(LocalDateTime dateTime) {
        return dateTime.format(PRETTY_FORMAT);
    }
    
    public static String standardFormatDateTime(LocalDateTime dateTime) {
        return dateTime.format(STANDARD_FORMAT);
    }
}
