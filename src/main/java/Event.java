public class Event extends Task {
    private String start;

    public Event(String taskName, String start) {
        super(taskName);
        this.start = start;
        setType('E');
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }
}
