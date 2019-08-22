public class DeadlinesTask extends Task {
    String taskName;
    String taskTime;

    public DeadlinesTask(String task) {
        super(task);
        String[] taskSplit = task.split("/");
        taskName = taskSplit[0].trim();
        String taskTimeOriginal = taskSplit[1];
        int firstWordLength = taskTimeOriginal.split(" ")[0].length();
        taskTime = taskTimeOriginal.split(" ")[0] + ": " + taskTimeOriginal.substring(firstWordLength).trim();
    }

    @Override
    public String toString() {
        String output = "[D]";
        if (super.completed) {
            output += "[✓]";
        } else {
            output += "[✗]";
        }
        output += " " + this.taskName + " (" + this.taskTime + ")";
        return output;
    }
}
