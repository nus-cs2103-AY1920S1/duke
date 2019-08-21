public class Event extends Task {
    private String info;

    public Event(String description, String info) {
        super(description);
        this.info = info;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at:" + info + ")";
    }
}
