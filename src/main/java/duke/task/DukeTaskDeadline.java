package duke.task;

public class DukeTaskDeadline extends DukeTask {
    private String taskDeadline;

    public DukeTaskDeadline(String taskName, String taskDeadline) {
        super(taskName, "D");
        this.taskDeadline = taskDeadline;
    }

    public DukeTaskDeadline(String taskName, boolean isComplete, String taskDeadline) {
        super(taskName, isComplete, "D");
        this.taskDeadline = taskDeadline;
    }

    public String getTaskDeadline() {
        return this.taskDeadline;
    }

    @Override
    public String toString() {
        String symbol = getTaskIsComplete() ? "\u2713" : "\u2718";
        return "[" + getTaskType() +"][" + symbol + "] " + getTaskName() + " (by: " + this.taskDeadline + ")";
    }

}
