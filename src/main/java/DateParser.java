import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;

public class DateParser {

    private final SimpleDateFormat formatter;

    public DateParser (String format) {
        formatter = new SimpleDateFormat(format);
    }

    public Date parse(String str) throws ParseException {
        return formatter.parse(str);
    }

    public String format(Date d) {
        return formatter.format(d);
    }
}