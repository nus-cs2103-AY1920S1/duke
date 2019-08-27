//Solution below adapted from https://nus-cs2103-ay1920s1.github.io/website/schedule/week2/project.html

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Deadline extends Task {

    protected Date by;
    protected String dateInString;
    protected static final String dateFormat = "dd/MM/yyyy HHmm";
    protected SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);

    public Deadline(String description, Calendar by) {
        super(description);
        this.by = by.getTime();
        dateInString = formatter.format(this.by);
    }

    public Deadline(String description, String dateInString) {
        super(description);
        this.dateInString = dateInString;
        try {
            by = formatter.parse(dateInString);
        } catch (ParseException ex) {
            System.out.println(ex.getCause());
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dateInString + ")";
    }
}