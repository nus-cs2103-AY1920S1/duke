import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Event extends Task {

    protected Date at;

    public Event(String description, String at) throws ParseException {
        super(description);
        this.at = new SimpleDateFormat("yyyy/MM/dd hh:mm").parse(at);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (at: " + at.toString() + ")";
    }
}
