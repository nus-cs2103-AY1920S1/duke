public class Deadline extends Task {
    protected String endTime;

    public Deadline(String description, int isDone, String endTime) {
        super(description, isDone);
        this.type = "D";
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + endTime + ")";
    }
}
