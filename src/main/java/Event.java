public class Event extends Task{

    protected String eventTime;
    final String TASK_TYPE = "[E]";

    public Event(String description, String eventTime) {
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
        return TASK_TYPE + super.getStatusIcon() + " " + super.toString() + " (at: " + eventTime + ")";
    }
}
