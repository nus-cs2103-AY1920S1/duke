package duke.task;

public class Event extends Task {

    private String eventTimeTo;
    private String eventTimeFrom;

    public Event(String eventName, String eventTimeTo, String eventTimeFrom) {
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
