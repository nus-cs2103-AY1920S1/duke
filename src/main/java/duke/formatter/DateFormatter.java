package duke.formatter;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatter {

    public static String format(Date date) {
        return new SimpleDateFormat("dd/MM/yyyy HHmm").format(date);
    }
}
