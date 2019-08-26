import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeParser {

    public static Date validateDateFormat(String dateToValidate) throws WrongDateFormatException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HHmm");
        //To make strict date format validation
        formatter.setLenient(false);
        Date parsedDate = null;
        try {
            parsedDate = formatter.parse(dateToValidate);
        } catch (ParseException e) {
            throw new WrongDateFormatException(dateToValidate);
        }
        return parsedDate;
    }
}
