import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DukeDate {
    private final String dateFormatString = "dd/MM/yyyy HH:mm";
    private SimpleDateFormat df;
    private Date time;

    public DukeDate(String dateStr) throws ParseException {
        this.df = new SimpleDateFormat(this.dateFormatString);
        this.time = this.df.parse(dateStr);
    }

    public String toString() {
        return this.df.format(this.time);
    }
}
