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
    public DeadlinesTask(String isCompleted, String taskName, String taskDesc) {
        super(taskName, Boolean.parseBoolean(isCompleted));
        this.taskName = taskName;
        this.taskDesc = taskDesc;
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

    @Override
    public String toFileFormat() {
        String output = "deadline | ";
        if (completed) {
            output += "true";
        } else {
            output += "false";
        }
        output += " | " + this.taskName + " | " + this.taskDesc;
        return output;
    }
}
