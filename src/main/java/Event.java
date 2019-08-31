import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;


public class Event extends Task {

    protected String at;
    LocalDateTime date1;
    LocalDateTime date2;

    public Event(String description, String at) {
        super(description);
        this.at = at;
        super.symbol = "E";

    }

    public void getDate() {
        String[] date_arr = at.split(" ");
        //DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        date1 = date1.parse(date_arr[0],formatter);
        date2 = date1.parse(date_arr[0],formatter);
        date1 = date1.withHour(Integer.parseInt(date_arr[1]) / 100).withMinute(Integer.parseInt(date_arr[1]) % 100);
        date2.withHour(Integer.parseInt(date_arr[2]) / 100).withMinute(Integer.parseInt(date_arr[2]) % 100);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}