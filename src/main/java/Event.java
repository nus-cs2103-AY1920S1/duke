public class Event extends Task {
    private String eventTime;

    public Event(String name) {
        super(name);
    }

    public Event(String name, String eT) {
        super(name);
        formatEventTime(eT);
    }
    public void formatEventTime(String eT) {
        String format = eT.substring(0,2) + ":" + eT.substring(2);
        this.eventTime = format;
    }

    public String toString() {
        return "[E]" + super.toString() + "(" + eventTime + ")";
    }
}
