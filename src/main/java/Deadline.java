public class Deadline extends Task {

    private String deadline;

    public Deadline(String taskName, String deadline) {
        super(taskName);
        this.deadline  = deadline;
    }

    public String toString() {
        if (done) {
            return "[D][✓]" + taskName + "(by:" + deadline + ")";
        } else {
            return "[D][✗]" + taskName + "(by:" + deadline + ")";
        }
    }
}
