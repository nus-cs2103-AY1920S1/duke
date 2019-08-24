package task;

public class Event extends Task {
    protected String time;

    /**
     * Creates a new event task.
     * @param description Description of the task
     * @param time Time of the event
     */
    public Event(String description, String time) {
        super(description);
        this.time = time;
    }

    @Override
    public String getTaskInitial() {
        return "E";
    }

    @Override
    public String extraText() {
        return " (at: " + this.time + ")";
    }
}
