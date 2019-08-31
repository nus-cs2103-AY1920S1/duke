package duke.task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import duke.task.Task;

public class Event extends Task {

    protected String time;
    protected Date date;

    public Event(String description, String time) throws ParseException {
        super(description);
        this.time = time;
        super.typeOfTask = "E";
        SimpleDateFormat formatter = new SimpleDateFormat( "dd/MM/yyyy hhmm");
        this.date = formatter.parse(time);
    }

    public String getTime() {
        return this.time;
    }

    public String toString() {
        return "[" + this.typeOfTask + "]" + "[" + this.getStatusIcon() + "] " + this.description + " (at: " + time + ")";
    }
}
