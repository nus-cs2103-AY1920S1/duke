package duke.task;

import duke.helper.DateTimeHelper;
import java.time.LocalDateTime;

public class Event extends Task{

    final String TASK_TYPE = "[E]";
    protected LocalDateTime eventTime;


    public Event(String description, LocalDateTime eventTime) {
        super(description);
        this.eventTime = eventTime;
    }

    public String getType() {
        return TASK_TYPE;
    }

    public String getDescription(){
        return this.description + "|" + DateTimeHelper.formatOutput(this.eventTime);
    }

    @Override
    public String toString() {
        return TASK_TYPE + super.getStatusIcon() + " " + super.toString() + " (at: " + DateTimeHelper.formatOutput(eventTime) + ")";
    }
}
