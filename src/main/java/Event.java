import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Event extends Task {
    private Date at;
    public Event(String description, String at) throws ParseException {
        super(description);
        Date date=new SimpleDateFormat("dd/MM/yyyy HHmm").parse(at);
        this.at = date;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + this.at + ")";
    }
}
