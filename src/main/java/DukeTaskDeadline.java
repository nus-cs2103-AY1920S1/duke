public class DukeTaskDeadline extends DukeTask {
    private String taskDeadline;

    public DukeTaskDeadline(String taskName, String taskDeadline) {
        super(taskName);
        this.taskDeadline = taskDeadline;
    }

    public String getTaskDeadline() {
        return this.taskDeadline;
    }

    @Override
    public String toString() {
        String symbol = getTaskIsComplete() ? "\u2713" : "\u2718";
        return "[D][" + symbol + "] " + getTaskName() + " (by: " + this.taskDeadline + ")";
    }

}
