import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

class Parser {

    /**
     * Handles the conversion of String to Time
     * @param  time in String
     * @return Date
     */

    public Date formatTime(String time) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HHmm");
        return sdf.parse(time);
    }
}