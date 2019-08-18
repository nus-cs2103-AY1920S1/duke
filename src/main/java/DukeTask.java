public class DukeTask {
    private String taskName;
    private boolean isComplete;

    public DukeTask(String taskName) {
        this.taskName = taskName;
        this.isComplete = false;
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
}
