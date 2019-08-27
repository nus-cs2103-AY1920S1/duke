import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;

public class Task implements Serializable {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markDone() {
        this.isDone = true;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }

    protected static Date parseDate(String s) {
        SimpleDateFormat parser = new SimpleDateFormat("dd/MM/yyyy HHmm");
        try {
            return parser.parse(s);
        } catch (ParseException e) {
            System.out.println(
                "Your date needs to be in dd/mm/yyyy hhmm format!"
            );
        }
        return new Date();
    }

    protected static String stringifyDate(Date d) {
        SimpleDateFormat formatter = new SimpleDateFormat(
            "dd MMM yyyy, hh:mm aaa"
        );
        return formatter.format(d);
    }
}
