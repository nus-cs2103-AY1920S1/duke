package duke.task;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Event extends Task {
    private String eventTime;
    private Date eventDate;

    public Event(String description, String eventTime) {
        super(description);
        this.eventTime = eventTime;
    }

    public Event(String description, Date eventDate) {
        super(description);
        this.eventDate = eventDate;
    }

    @Override
    public String getOutputFormat() {
        if (eventTime == null) {
            SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy HHmm");
            return String.format("E | %d | %s | %s", isDone ? 1 : 0, description, dateFormatter.format(eventDate));
        }
        return String.format("E | %d | %s | %s", isDone ? 1 : 0, description, eventTime);
    }

    @Override
    public String toString() {
        if (eventDate != null) {
            DateFormat dtf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            return String.format("[E]%s (at: %s)", super.toString(), dtf.format(eventDate));
        } else {
            return String.format("[E]%s (at: %s)", super.toString(), eventTime);
        }
    }
}
