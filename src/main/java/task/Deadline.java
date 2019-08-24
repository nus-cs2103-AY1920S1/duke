package task;

public class Deadline extends TimedTask {
    public Deadline(String description, String deadline) {
        super(description, deadline);
    }

    @Override
    public String getTaskInitial() {
        return "D";
    }

    @Override
    public String extraText() {
        return " (by: " + this.time + ")";
    }

    @Override
    protected String extraSaveText() {
        return " | " + this.time;
    }
}
