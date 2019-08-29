import java.text.ParseException;
import java.text.SimpleDateFormat;

class Parser {
    public void formatTime(String time) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HHmm");
        sdf.parse(time);
    }
}