package duke.task;

/**
 * Task is an abstract class from which other tasks are built upon.
 * Tasks represents the tasks that a person has when using Duke.
 */
public abstract class Task implements Comparable<Task> {
    String taskDetails;
    boolean completed;
    PriorityLevel priority;

    Task(String taskDetails) {
        this.taskDetails = taskDetails;
        this.priority = PriorityLevel.LOW;
        this.completed = false;
    }

    /**
     * Marks a task as completed.
     */
    public void taskDone() {
        this.completed = true;
    }

    /**
     * Set the priority level of a task.
     */
    public void setPriority(PriorityLevel p) {
        this.priority = p;
    }

    /**
     * Get the priority level of a task.
     */
    public PriorityLevel getPriority() {
        return this.priority;
    }

    /**
     * Returns a string of a task that can contain
     * its description, time and completion status.
     *
     * @return string that contains information about a task.
     */
    abstract String saveInfo();

    /**
     * Returns the completion status of a task.
     *
     * @return task completion status.
     */
    public boolean isCompleted() {
        return this.completed;
    }

    /**
     * A getter for task details.
     *
     * @return the task details.
     */
    public String getTaskDetails() {
        return this.taskDetails;
    }

    @Override
    public String toString() {
        if (completed) {
            return "[\u2713] " + this.taskDetails + " " + priority.toString();
        } else {
            return "[\u2717] " + this.taskDetails + " " + priority.toString();
        }
    }

    @Override
    public int compareTo(Task anotherTask) {
        PriorityLevel otherPriority = anotherTask.getPriority();
        switch (this.priority) {
        case HIGH:
            if (otherPriority == PriorityLevel.HIGH) {
                return 0;
            } else {
                return -1;
            }
        case MEDIUM:
            if (otherPriority == PriorityLevel.MEDIUM ) {
                return 0;
            } else if (otherPriority == PriorityLevel.LOW){
                return -1;
            } else {
                return 1;
            }
        case LOW:
            if (otherPriority == PriorityLevel.LOW ) {
                return 0;
            } else {
                return 1;
            }
        default:
            return 0;
        }
    }
}
