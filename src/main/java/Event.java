public class Event extends Task {

    private String eventDate;
    private String eventStartEnd;

    public Event (String eventName, String eventDate, String eventStartEnd) {
        super(eventName);
        this.eventDate = eventDate;
        this.eventStartEnd = eventStartEnd;
    }

    @Override
    public String getTaskName() {
        return super.getTaskName() + "( " + eventDate + " " + eventStartEnd + " )";
    }

    @Override
    public char getRepLetter() {
        return 'E';
    }
}
