import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utilities {
    public static Date dateParser(String dateStr) throws DukeException {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HHmm");
            Date date = formatter.parse(dateStr);
            return date;
        } catch (ParseException e) {
            throw new DukeException("Failed to parse date.");
        }
    }
}
