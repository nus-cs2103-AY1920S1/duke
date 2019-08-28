//Solution below adapted from https://nus-cs2103-ay1920s1.github.io/website/schedule/week2/project.html

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Event extends Task {

    protected Date at;
    protected String dateInString;
    protected static final String dateFormat = "dd/MM/yyyy HHmm";
    protected SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);

    public Event(String description, Calendar at) {
        super(description);
        this.at = at.getTime();
        dateInString = formatter.format(this.at);
    }

    public Event(String description, String dateInString) {
        super(description);
        this.dateInString = dateInString;
        try {
            at = formatter.parse(dateInString);
        } catch (ParseException ex) {
            System.out.println(ex.getCause());
        }
    }

    @Override
    public String toString() {
        return "[E]" + " " + super.toString() + " (at: " + dateInString + " )";
    }
}