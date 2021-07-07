package weijie.duke.utils;

import java.time.format.DateTimeFormatter;

public class DateUtils {
    public static final DateTimeFormatter DUKE_DATETIME_PARSE_FORMAT =
            DateTimeFormatter.ofPattern("d/M/yyyy HHmm");

    public static final DateTimeFormatter DUKE_DATETIME_OUTPUT_FORMAT =
            DateTimeFormatter.ofPattern("dd MMM yyyy, hh:kk a");
}
