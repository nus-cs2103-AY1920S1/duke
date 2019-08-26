package duke.task;

import java.util.Date;

public class Event extends Task {

    private Date eventTimeTo;
    private Date eventTimeFrom;

    public Event(String eventName, Date eventTimeTo, Date eventTimeFrom) {
        super(eventName);
        this.eventTimeFrom = eventTimeFrom;
        this.eventTimeTo = eventTimeTo;
    }

    @Override
    public String getTaskName() {
        return super.getTaskName() + " (at: " + eventTimeFrom + " - " + eventTimeTo + ")";
    }

    @Override
    public char getRepLetter() {
        return 'E';
    }

}
