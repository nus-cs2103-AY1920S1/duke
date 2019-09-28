package duke.task;

import duke.command.Commands;

public abstract class Task {
    private String taskName;
    private String taskIcon;
    private Commands taskType;
    private boolean isDone;

    /**
     * Constructor.
     * @param taskName - Name of given task
     * @param taskIcon - Icon for the task - [D]/[E] etc..
     * @param taskType - Type of task - Deadline/Event etc..
     */
    public Task(String taskName, String taskIcon, Commands taskType) {
        this.taskName = taskName;
        this.isDone = false;
        this.taskIcon = taskIcon;
        this.taskType = taskType;
    }

    /** Marks duke.task.Task as Done. **/
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Returns taskName.
     * @return String name
     */
    public String getName() {
        return this.taskName;
    }

    /**
     * Returns tick if task is Done, else returns cross.
     * @return String symbol
     */
    private String getDoneSymbol() {
        if (this.isDone) {
            return "Done";
        }
        return "Not done";
    }

    /** Returns task type. **/
    public Commands getTaskType() {
        return this.taskType;
    }

    /** Returns true if task is done, else returns false. **/
    public boolean isDone() {
        return this.isDone;
    }

    /** Returns task icon. **/
    private String getTaskIcon() {
        return this.taskIcon;
    }

    /** Returns a copy of the existing task. **/
    public abstract Task getTaskCopy();

    @Override
    public String toString() {
        return this.getTaskIcon() + "[" + this.getDoneSymbol() + "] " + this.getName();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Task) {
            Task task = (Task) obj;
            return (task.getName().equals(this.getName())
                    && (task.getTaskType().equals(this.getTaskType()))
                    && (task.isDone() == this.isDone()));
        }
        return false;
    }
}
