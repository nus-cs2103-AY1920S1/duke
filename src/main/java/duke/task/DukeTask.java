package duke.task;

public class DukeTask {
    private String taskName;
    private String taskType;
    private boolean isComplete;

    public DukeTask(String taskName) {
        this.taskName = taskName;
        this.isComplete = false;
    }

    public DukeTask(String taskName, String taskType) {
        this.taskName = taskName;
        this.taskType = taskType;
        this.isComplete = false;
    }

    public DukeTask(String taskName, boolean isComplete, String taskType) {
        this.taskName = taskName;
        this.isComplete = isComplete;
        this.taskType = taskType;
    }

    public String getTaskName() {
        return this.taskName;
    }

    public String getTaskType() {
        return this.taskType;
    }

    public boolean getTaskIsComplete() {
        return this.isComplete;
    }

    public void setTaskComplete() {
        this.isComplete = true;
    }
}
