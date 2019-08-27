import java.time.LocalDateTime;

public class Event extends Task{
<<<<<<< HEAD

    protected String eventTime;
    final String TASK_TYPE = "[E]";
=======
    protected LocalDateTime eventTime;
>>>>>>> branch-Level-8

    public Event(String description, LocalDateTime eventTime) {
        super(description);
        this.eventTime = eventTime;
    }

    public String getType() {
        return TASK_TYPE;
    }

    public String getDescription(){
        return this.description + "|" + this.eventTime;
    }

    public String getTime() {
        return eventTime;
    }

    @Override
    public String toString() {
<<<<<<< HEAD
        return TASK_TYPE + super.getStatusIcon() + " " + super.toString() + " (at: " + eventTime + ")";
=======
        return "[E]" + super.getStatusIcon() + " " + super.toString() + " (at: " + DateTimeHelper.formatOutput(eventTime) + ")";
>>>>>>> branch-Level-8
    }
}
