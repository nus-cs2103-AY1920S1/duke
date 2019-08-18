public class DukeTask {
    private String taskName;
    private boolean isComplete;

    public DukeTask(String taskName) {
        this.taskName = taskName;
        this.isComplete = false;
    }

    public DukeTask(String taskName, boolean isComplete) {
        this.taskName = taskName;
        this.isComplete = isComplete;
    }

    public String getTaskName() {
        return this.taskName;
    }

    public boolean getTaskIsComplete() {
        return this.isComplete;
    }

    public void setTaskComplete() {
        this.isComplete = true;
    }

    @Override
    public String toString() {
        String symbol = this.isComplete ? "\u2713" : "\u2718";
        return "[" + symbol + "] " + this.taskName;
    }
}
