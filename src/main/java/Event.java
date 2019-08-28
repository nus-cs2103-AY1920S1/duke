class Event extends Task{
    private String eventAt;
    Event(String taskDetails, String eventAt) {
        super(taskDetails);
        this.eventAt = eventAt;
    }

    String saveInfo() {
        return "event" + " " + taskDetails + " /" + eventAt + System.getProperty("line.separator")
                + completed;
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
