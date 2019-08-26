package duke.task;

public class Event extends Task {

    private String eventTimeTo;
    private String eventTimeFrom;

    public Event(String eventName, String eventTimeFrom, String eventTimeTo) {
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

    @Override
    public String toDelimitedString() {
        return String.format("%c | %c | %s | %s | %s", this.getRepLetter(), this.isDone() ? 'T' : 'F', super.getTaskName(), this.eventTimeFrom, this.eventTimeTo);
    }

}
