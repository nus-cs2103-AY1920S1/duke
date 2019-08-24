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
    protected String extraText() {
        return " (at: " + this.time + ")";
    }

    @Override
    protected String extraSaveText() {
        return " | " + this.time;
    }
}
