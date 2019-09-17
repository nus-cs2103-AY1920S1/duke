package duke.task;

import duke.task.Task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.util.Date;

public class Event extends Task {

    protected String at;
    private SimpleDateFormat eventTime;
    private Date date;

    public Event(String description, String at) throws DateTimeException {
        super(description);
        this.at = at.trim();
        this.event = "event";
        this.type = "E";

        eventTime = new SimpleDateFormat("dd/MM/yyyy HHmm");
    }

    @Override
    public String getType() {
        return this.type;
    }

    @Override
    public String getAt() {
        return this.at;
    }

    public String convertEventTime() throws ParseException, DateTimeException {
        this.date = eventTime.parse(this.at);
        return this.eventTime.format(date);
    }
    @Override
    public String toString() {
        try {
            return "[E]" + super.toString() + " (at: " + convertEventTime() + ")";
        } catch (ParseException exception) {
            return "Error";
        }
    }
}
