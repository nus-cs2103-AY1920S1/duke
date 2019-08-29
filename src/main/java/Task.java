import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;

public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected Date date;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    public int getStatusCode() {
        return isDone ? 1 : 0;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    protected static Date parseDate(String dateString) throws DukeException {
        try {
            Date date = new SimpleDateFormat("dd/MM/yyyy HHmm").parse(dateString);
            return date;
        } catch (ParseException e) {
            throw new DukeException("Cannot parse date/time.");
        }
    }

    protected static String formatDate(Date date) {

        return new SimpleDateFormat("dd/MM/yyyy HHmm").format(date);
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    public abstract String textFormat();

}
