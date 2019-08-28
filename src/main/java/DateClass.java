import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateClass {

    public static Date stringToDate (String string) {

        SimpleDateFormat format = new SimpleDateFormat("d/MM/yyyy HHmm");

        Date date = null;

        try {
            date = format.parse(string);
        } catch (ParseException e) {
            
        }

        return date;
    }
}
