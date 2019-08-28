import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;

public class DateParser {

    public static final String DATE_FORMAT = "dd-MM-yy HHmm";
    private final SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat(DATE_FORMAT);

    public Date parse(String str) throws ParseException {
        return DATE_FORMATTER.parse(str);
    }

    public String format(Date d) {
        return DATE_FORMATTER.format(d);
    }
}