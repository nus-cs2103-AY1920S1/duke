package dude.task;

public class Deadline extends Task {
    private String endTime;

    /**
     * Initializes a Deadline object.
     *
     * @param description Description of the Deadline.
     * @param isDone Completion status of the Deadline task.
     * @param endTime Time that the task must be completed by.
     */
    public Deadline(String description, int isDone, String endTime) {
        super(description, isDone);
        this.type = "D";
        this.endTime = endTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: " + endTime + ")";
    }
}
