import java.text.SimpleDateFormat;
import java.util.Date;

public class StringDateConverter {
    public Date convertStringToDate(String strDate) throws Exception {
        SimpleDateFormat dateWithTime = new SimpleDateFormat("dd/MM/yyy HHmm");
        Date date = dateWithTime.parse(strDate);
        return date;
    }

    public Date convertLongStringToDate(String strDate) throws Exception {
        SimpleDateFormat dateWithTime = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
        Date date = dateWithTime.parse(strDate);
        return date;
    }
}
