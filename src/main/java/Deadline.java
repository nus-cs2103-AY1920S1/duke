public class Deadline extends Task {

    private String deadline;

    public Deadline(String taskName, boolean done, String deadline) {
        super(taskName, done);
        this.deadline  = deadline;
    }

    public String toString() {
        if (done) {
            return "[D][✓]" + taskName + "(by:" + deadline + ")";
        } else {
            return "[D][✗]" + taskName + "(by:" + deadline + ")";
        }
    }

    public String storageFormat() {
        if (done) {
            return "D/✓/" + taskName + "/" + deadline;
        } else {
            return "D/✗/" + taskName + "/" + deadline;
        }
    }
}
