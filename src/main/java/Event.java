public class Event extends Task {

    private char taskType = 'E';
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    public Event(String status, String description, String at) {
        super(description);
        this.setStatus(status);
        this.at = at;
    }

    @Override
    public String toString() {
        return String.format("[E][%s] " + super.toString() + " (at: " + at + ")", super.getStatusIcon());
    }

    @Override
    public char getTaskType() {
        return taskType;
    }

    @Override
    public String getDate() {
        return at;
    }
}
