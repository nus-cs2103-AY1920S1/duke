import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTime{
    protected String input;
    protected Date date;

    public DateTime(String input) throws ParseException {
        String dateFormat = "dd-MM-yyyy HH:mm";
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
        this.input = input;
        this.date = formatter.parse(input);
    }

    @Override
    public String toString() {
        String dateFormat = "dd-MM-yyyy HH:mm";
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
        return formatter.format(this.date);
    }
}