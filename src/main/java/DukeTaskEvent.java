public class DukeTaskEvent extends DukeTask {
    private String taskLocation;

    public DukeTaskEvent(String taskName, String taskLocation) {
        super(taskName, "E");
        this.taskLocation = taskLocation;
    }

    public DukeTaskEvent(String taskName, boolean isComplete, String taskLocation) {
        super(taskName, isComplete, "E");
        this.taskLocation = taskLocation;
    }

    public String getTaskLocation() {
        return this.taskLocation;
    }

    @Override
    public String toString() {
        String symbol = getTaskIsComplete() ? "\u2713" : "\u2718";
        return "[" + getTaskType() +"][" + symbol + "] " + getTaskName() + " (at: " + this.taskLocation + ")";
    }
}
