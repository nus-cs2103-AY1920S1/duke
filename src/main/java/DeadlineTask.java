public class DeadlineTask extends Task {

    public DeadlineTask(String description, String deadline) {
        super(description);
        this.dateTime = deadline;
        this.type = "D";
    }

    public DeadlineTask(String description, String deadline, boolean isDone) {
        super(description, isDone);
        this.dateTime = deadline;
        this.type = "D";
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.dateTime);
    }
}
