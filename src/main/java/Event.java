import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Event extends Task {

    private Date at;

    Event(String description, String at) throws ParseException {
        super(description);
        this.at = new SimpleDateFormat("yyyy/MM/dd HH:mm").parse(at);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (at: " + new SimpleDateFormat("yyyy/MM/dd HH:mm").format(at) + ")";
    }
}
