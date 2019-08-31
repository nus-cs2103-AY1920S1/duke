import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateParser {

    public static Date parse(String dateTime) {
        try {
            return new SimpleDateFormat("dd/MM/yyyy HHmm").parse(dateTime);
        }  catch(ParseException pe) {
            throw new InvalidDateTimeException(dateTime);
        }
    }
}
