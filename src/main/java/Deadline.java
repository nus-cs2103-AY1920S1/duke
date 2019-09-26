public class Deadline extends Task {

    private char taskType = 'D';
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public Deadline(String status, String description, String by) {
        super(description);
        this.setStatus(status);
        this.by = by;
    }

    @Override
    public String toString() {
        return String.format("[D][%s] " + super.toString() + " (by: " + by + ")", super.getStatusIcon());
    }

    @Override
    public char getTaskType() {
        return taskType;
    }

    @Override
    public String getDate() {
        return by;
    }
}
