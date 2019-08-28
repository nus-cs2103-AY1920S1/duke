import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class DukeDateFormatter {
    public static Date parse(String str) {
        try {
            return new SimpleDateFormat("dd/MM/yyyy HHmm").parse(str);
        } catch(ParseException ex) {
            System.out.println(ex);
        }
        return null;
    }

    public static String format(Date date) {
        return new SimpleDateFormat("dd/MM/yyyy HHmm").format(date);
    }
}
