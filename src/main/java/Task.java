import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Task {

    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    private Date parseDate(String input) throws ParseException {
        SimpleDateFormat ft = new SimpleDateFormat ("dd/MM/yyyy HHmm");
        Date t = new Date();
        t = ft.parse(input);
        return t;
    }

    protected String formatDescription() throws ParseException {
        String item = description.substring(0, description.indexOf("/"));
        String tag = description.substring(description.indexOf("/") + 1);
        String prep = tag.substring(0, tag.indexOf(" "));
        String time = tag.substring(tag.indexOf(" ") + 1);
        Date date = parseDate(time);
        return item + "(" + prep + ": " + date + ")";
    }

    protected String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public String repr() throws ParseException {
        return "[" + getStatusIcon() + "] " + description;
    }
}
