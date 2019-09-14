package duke.task;

/**
 * Task object class.
 * Inherited by Event, Deadline and ToDo.
 */

public class Task {

    protected String taskName;
    protected boolean isDone;
    protected TaskPriority taskPriority;


    /**
     * Constructor for Task.
     * @param name taskName.
     */
    public Task(String name) {
        this.isDone = false;
        this.taskName = name;
        this.taskPriority = TaskPriority.NORMAL;
    }

    @Override
    public String toString() {
        return String.format("[%s][%s] %s (%s)",
                this.getType(), isDone ? "1" : "0",
                taskName, getPriorityString());
    }

    public int getPriorityValue() {
        return taskPriority.getValue();
    }

    public String getPriorityString() {
        return taskPriority.toString().toLowerCase();
    }

    /**
     * Set taskPriority according to value given.
     * @param value Priority Number.
     */
    public void setPriority(int value) {
        switch (value) {
        case 1:
            taskPriority = TaskPriority.EMERGENCY;
            break;
        case 2:
            taskPriority = TaskPriority.URGENT;
            break;
        case 3:
            taskPriority = TaskPriority.STANDARD;
            break;
        case 4:
            taskPriority = TaskPriority.NORMAL;
            break;
        default:
            taskPriority = TaskPriority.NORMAL;
            break;
        }
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String getType() {
        return " ";
    }

    public boolean isDone() {
        return this.isDone;
    }

    public String getTaskName() {
        return this.taskName;
    }
}
