public class Deadline extends Task {

    String deadline;

    Deadline(String taskName, boolean isDone, String deadline) {
        super(taskName, isDone);
        this.deadline = deadline;
    }

    @Override
    public String toFile(){
        String mark = isDone ? "1" : "0";
        return "D | " + mark + " |" + taskName + "|" + deadline;
    }

    @Override
    public String toString(){
        String mark = isDone ? "✓" : "✗";
        return "[D][" + mark + "]" + taskName +
                "(by:" + deadline + ")";
    }

}