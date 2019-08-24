import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;

public class DateParser {

    public static final String DATE_FORMAT = "dd/MM/yy hhmm";
    private static final SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat(DATE_FORMAT);

    public static Date parse(String str) throws ParseException {
        return DATE_FORMATTER.parse(str);
    }
}