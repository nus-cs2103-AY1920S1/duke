public class Task implements ITask {
    private static final String DEFAULT_TASK_TYPE = "Default";
    private String taskName;
    private String taskIcon;
    private String taskType;
    private boolean isDone;

    public Task(String taskName, String taskIcon, String taskType) {
        this.taskName = taskName;
        this.isDone = false;
        this.taskIcon = taskIcon;
        this.taskType = taskType;
    }

    /**
     * Marks Task as Done
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Returns taskName
     * @return String name
     */
    public String getName() {
        return this.taskName;
    }

    /**
     * Returns tick if task is Done, else returns cross
     * @return String symbol
     */
    private String getDoneSymbol() {
        if(this.isDone) {
            return "✓";
        }
        return "✗";
    }

    public String getTaskType() {
        return this.taskType;
    }

    public boolean isDone() {
        return this.isDone;
    }

    private String getTaskIcon() {
        return this.taskIcon;
    }

    @Override
    public String toString() {
        return this.getTaskIcon() + "[" + this.getDoneSymbol() + "] " + this.getName();
    }
}
