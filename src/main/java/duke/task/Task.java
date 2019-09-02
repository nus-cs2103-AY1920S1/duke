package duke.task;

public abstract class Task {
    private static final String DEFAULT_TASK_TYPE = "Default";
    private String taskName;
    private String taskIcon;
    private String taskType;
    private boolean isDone;

    /**
     * Constructor
     * @param taskName - Name of given task
     * @param taskIcon - Icon for the task - [D]/[E] etc..
     * @param taskType - Type of task - Deadline/Event etc..
     */
    public Task(String taskName, String taskIcon, String taskType) {
        this.taskName = taskName;
        this.isDone = false;
        this.taskIcon = taskIcon;
        this.taskType = taskType;
    }

    /** Marks duke.task.Task as Done **/
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

    /** Returns task type **/
    public String getTaskType() {
        return this.taskType;
    }

    /** Returns true if task is done, else returns false **/
    public boolean isDone() {
        return this.isDone;
    }

    /** Returns task icon **/
    private String getTaskIcon() {
        return this.taskIcon;
    }

    @Override
    public String toString() {
        return this.getTaskIcon() + "[" + this.getDoneSymbol() + "] " + this.getName();
    }
}
