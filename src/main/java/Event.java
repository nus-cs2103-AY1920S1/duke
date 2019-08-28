import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class Event extends Task {
    private Date at;

    public Event(String description, String at) {
        super(description);
        try {
            this.at = new SimpleDateFormat("dd/MM/yyyy HHmm").parse(at);
        } catch(ParseException ex) {
            System.out.print(ex);
        }
    }

    public String encode() {
        return "event," + super.description + "," + super.isDone + "," + at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at:" + at + ")";
    }

}
