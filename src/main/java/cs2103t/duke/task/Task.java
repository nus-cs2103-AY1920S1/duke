package cs2103t.duke.task;

public abstract class Task {
    public static final String TICK = "\u2713";
    public static final String CROSS = "\u2717";

    protected String description;
    protected boolean completed;

    protected TaskType taskType;

    //getter mtds
    public String getDescription() {
        return this.description;
    }
    public boolean isCompleted() {
        return this.completed;
    }
    public TaskType getTaskType() { return this.taskType; }
    //setter mtds
    public void setCompleted() {
        this.completed = true;
    }

    @Override
    public String toString() {
        String checked;
        if (this.completed) {
            checked = TICK;
        } else {
            checked = CROSS;
        }
        return String.format("[%s][%s] %s", this.taskType, checked, this.description);
    }
}
