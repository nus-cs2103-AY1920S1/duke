public class Deadline extends Task {

    String deadline;

    Deadline(String taskName, boolean isDone, String deadline) {
        super(taskName, isDone);
        this.deadline = deadline;
    }

    @Override
    public String toString(){
        String mark = isDone ? "✓" : "✗";
        return "[D][" + mark + "] " + taskName +
                " (by:" + deadline + ")";
    }

}