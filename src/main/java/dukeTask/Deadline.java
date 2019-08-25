package dukeTask;

public class Deadline extends Task {
    private String endTime;

    public Deadline(String description, int isDone, String endTime) {
        super(description, isDone);
        this.type = "D";
        this.endTime = endTime;
    }

    public String getEndTime() {
        return endTime;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + endTime + ")";
    }
}
