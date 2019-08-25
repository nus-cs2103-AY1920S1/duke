import java.text.SimpleDateFormat;
import java.util.Date;

public class StringToDateConverter {
    public Date convertStringToDate(String strDate) throws Exception {
        SimpleDateFormat dateWithTime = new SimpleDateFormat("dd/MM/yyy HHmm");
        Date date = dateWithTime.parse(strDate);
        return date;
    }
}
