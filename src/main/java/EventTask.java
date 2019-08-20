public class EventTask extends Task {
    protected String time;

    public EventTask(String description, String time) {
        super(description);
        this.time = time;
    }

    @Override
    public String toString() {
        return "[E][" + this.getStatusIcon() + "] " + this.description
                + " (at: " + this.time + ")";
    }
}
