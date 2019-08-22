class Event extends Task{
    private String eventAt;
    Event(String taskDetails, String eventAt) {
        super(taskDetails);
        this.eventAt = eventAt;
    }

    @Override
    public String toString() {
        if (this.completed) {
            return "[D][✓] " + taskDetails + " (" + eventAt + ")";
        } else {
            return "[D][✗] " + taskDetails + " (" + eventAt + ")";
        }
    }
}
