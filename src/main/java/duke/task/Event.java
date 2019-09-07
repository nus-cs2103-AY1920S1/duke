package duke.task;

import duke.datetime.DateTime;

public class Event extends Task {
    private DateTime eventTime;
    private static final String ABBREV_TASK = "E";

    public Event(String description, DateTime eventTime) {
        super(description);
        this.eventTime = eventTime;
    }

    @Override
    public String toSaveFormat() {
        return ABBREV_TASK + " | " + taskIsDoneState + " | " + description + " | " + eventTime.toSaveFormat();
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + eventTime + ")";
    }
}