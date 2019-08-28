package duke.task;

/**
 * Represents an actual task that is to be completed by the User.
 */
public abstract class Task {
    private String description;
    private boolean isDone;
    /** Total number of tasks tracked by Duke */
    public static int totalTasks = 0;

    /**
     * Class constructor specifying the description of the task.
     * Task is not completed by default.
     *
     * @param description Description of task being created.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Class constructor specifying description and completion status of
     * the Task.
     * @param description Description of task being created.
     * @param done Completion status of task: 1 = done; 0 = not done.
     */
    public Task(String description, int done) {
        this.description = description;
        this.isDone = done == 1;

    }

    /**
     * Returns an icon represent completion status of Task.
     *
     * @return Either tick or X symbols as a String.
     */
    private String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    /**
     * Marks the Task as completed.
     */
    public void completeTask(){
        isDone = true;
    }

    /**
     * Returns the completion status of Task.
     *
     * @return Completion status of Task
     */
    public boolean getDoneStatus(){
        return isDone;
    }

    /**
     * Returns description of Task.
     *
     * @return Description of Task.
     */
    public String getDescription(){
        return description;
    }

    /**
     * Returns String representation of Task, indicating
     * its completion status.
     *
     * @return String representation of Task.
     */
    @Override
    public String toString(){
        return "[" + getStatusIcon() + "]" + " " + description;
    }

}
