import java.util.Date;

/** Class to represent a task. */
abstract class Task {
    protected String name;
    protected boolean done;
    protected TaskType type;
    protected Date date;

    /**
     * Constructor for the object.
     * @param name Description of the task.
     */
    public Task(String name) {
        this.name = name;
    }

    /**
     * Mark a task as done.
     */
    public void markDone() {
        this.done = true;
    }

    /**
     * Get the task description.
     * @return String representing task description.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Check if task is marked done.
     * @return Boolean representing task done status.
     */
    public boolean isTaskDone() {
        return this.done;
    }

    /**
     * Method to represent the task type.
     * @return TaskType of the task.
     */
    public TaskType getType() {
        return this.type;
    }

    /**
     * Abstract method representing the task date.
     * @return Date of task.
     */
    public abstract Date getDate();

    /**
     * Function for updating date of a task.
     * @param date New date for the task.
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Function for updating name of a task.
     * @param date New name for the task.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Function for updating name and date of a task.
     * @param name New name for the task.
     * @param date New date for the task.
     */
    public void setBoth(String name, Date date) {
        this.name = name;
        this.date = date;
    }
}