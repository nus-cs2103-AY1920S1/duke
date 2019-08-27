import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Deadline extends Task {

    protected String by;
    protected Date date;
    protected int hour;
    protected int minute;

    public Deadline(String description, String by) throws ParseException {
        super(description);
        this.by = by;
        String[] words = by.split(" ");
        date = new SimpleDateFormat("dd/MM/yyyy").parse(words[0]);
        hour = (Integer.parseInt(words[1])/100)%12;
        minute = (Integer.parseInt(words[1])%100);
    }

    @Override
    public String getDescription() {
        return description + " /by " + by;
    }

    @Override
    public String getType() {
        return "D";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}