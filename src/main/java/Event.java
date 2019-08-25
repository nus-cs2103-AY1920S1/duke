import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;

public class Event extends Task {
    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HHmm");
    protected Date time;

    public Event (String description, String time) throws ParseException {
        super(description);

        this.time = format.parse(time);
    }

    public String toFileString() {
        return "E||" + (this.isDone?"1||":"0||")  + this.description + "||" + format.format(this.time);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at:" +  time  + ")";
    }
}