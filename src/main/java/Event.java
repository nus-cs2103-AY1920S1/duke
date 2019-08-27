import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Event extends Task {

    protected String at;
    protected Date date;
    protected int hour;
    protected int minute;

    public Event(String description, String at) throws ParseException {
        super(description);
        this.at = at;
        String[] words = at.split(" ");
        date = new SimpleDateFormat("dd/MM/yyyy").parse(words[0]);
        hour = (Integer.parseInt(words[1])/100)%12;
        minute = (Integer.parseInt(words[1])%100);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}