import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Event extends Task {

    protected Date at;
    
    /**
     * Events class as part of Task.
     */
    public Event(String description, String at) throws ParseException {
        super(description);
        SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy HHmm");
        Date date1 = date.parse(at);
        this.at = date1;
    }

    @Override
    /**
     * To print out message in string.
     */
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
