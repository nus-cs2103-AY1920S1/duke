public class DeadlinesTask extends Task {
    String taskName;
    String taskDesc;

    public DeadlinesTask(String task) {
        super(task);
        String[] taskSplit = task.split("/by");
        if(taskSplit.length < 2) {
            throw new EmptyDescriptionDukeException("deadline", "/by");
        }
        taskName = taskSplit[0].trim();
        taskDesc = taskSplit[1];
    }

    @Override
    public String toString() {
        String output = "[D]";
        if (super.completed) {
            output += "[✓]";
        } else {
            output += "[✗]";
        }
        output += " " + this.taskName + " (By: " + this.taskDesc + ")";
        return output;
    }
}
