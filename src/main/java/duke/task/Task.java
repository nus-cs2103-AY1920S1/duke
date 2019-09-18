package duke.task;

/**
 * An abstract class for tasks.
 */
public abstract class Task implements Comparable<Task> {
    String description;
    boolean completed;
    PriorityLevel priority;

    /**
     * Creates a new Task with a task description.
     *
     * @param description The task's description.
     */
    Task(String description) {
        this.description = description;
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
     *
     * @return The PriorityLevel of the task.
     */
    public PriorityLevel getPriority() {
        return this.priority;
    }

    /**
     * Returns a string of a task that can contain
     * its type, completion status, description, time
     * and PriorityLevel.
     *
     * @return A string that contains full information about a task.
     */
    public abstract String saveInfo();

    /**
     * Returns the completion status of a task.
     *
     * @return The task's completion status.
     */
    public boolean isCompleted() {
        return this.completed;
    }

    /**
     * A getter for task details.
     *
     * @return The task description.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns a string containing full information of the task.
     *
     * @return A string representation of the task.
     */
    @Override
    public abstract String toString();

    /**
     * Compares two Task objects by their PriorityLevel.
     *
     * @return the int 0 if this Task has same PriorityLevel as the Task in argument;
     * -1 if PriorityLevel of this Task is higher than the Task in argument;
     * 1 if the PriorityLevel of this Task is lower than the Task in argument.
     */
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
