public class EventTask extends Task {
    protected String atTime;

    public EventTask(String description, String atTime) {
        super(description);
        this.atTime = atTime;
    }

    public String getTime() {
        return this.atTime;
    }

    @Override
    public String toString() {
        // Adds the type of the Task and its time period to the base toString() representation
        return String.format("[E]%s (at: %s)", super.toString(), this.atTime);
    }
}
