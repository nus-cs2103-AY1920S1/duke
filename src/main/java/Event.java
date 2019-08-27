public class Event extends Task {
    private String eventTime;

    public Event(String name) {
        super(name);
    }

    public Event(String name, String eT) {
        super(name);
        this.eventTime = eT;
    }


    public String toString() {
        return "E|" + super.toString().trim() + "|" + eventTime.trim();
    }
}
