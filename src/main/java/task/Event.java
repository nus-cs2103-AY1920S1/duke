package task;

import java.util.Date;

public class Event extends Task {
    private Date startPeriod;
    private Date endPeriod;

    public Event(String description, Date startPeriod, Date endPeriod) {
        super(description);
        this.startPeriod = startPeriod;
        this.endPeriod = endPeriod;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + String.format(" (at: %s - %s )", startPeriod, endPeriod);
    }
}
