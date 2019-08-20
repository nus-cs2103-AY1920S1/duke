public class Event extends Task {

    private String eventTime;

    public Event (String eventName, String eventTime) {
        super(eventName);
        this.eventTime = eventTime;
    }

    @Override
    public String getTaskName() {
        return super.getTaskName() + " (at: " + eventTime + ")";
    }

    @Override
    public char getRepLetter() {
        return 'E';
    }

}
