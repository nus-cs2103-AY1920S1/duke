package slave.task;

/**
 * Abstract class which represents a task
 */
public abstract class Task {

    private String description;
    private boolean isDone;
    private int id;
    TaskType type;

    /**
     * Constructor for task
     * @param description task description
     * @param id task id
     */
    public Task(String description, int id) {
        this.description = description;
        this.isDone = false;
        this.id = id;
    }

    /**
     * Decrement id of task
     */
    public void decrementId() {
        this.id--;
    }

    /**
     * getter method for task id
     * @return task id
     */
    public int getId() {
        return this.id;
    }

    /**
     * getter method for task type
     * @return task type
     */
    public TaskType getType() {
        return this.type;
    }

    /**
     * getter method for task description
     * @return task description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * getter method for task date
     * @return task date
     */
    public String getDate() {
        return "";
    }

    /**
     * getter method for appropriate status icon to represent whether a task has been done
     * @return Corresponding status icon
     */
    public String getStatusIcon() {
        return (isDone ? "Done" : "X");
    }

    /**
     * getter method for whether task is done
     * @return boolean value of whether a task is done
     */
    public boolean getIsDone(){
        return this.isDone;
    }

    /**
     * setter method for task to be done
     */
    public void setDone() {
        this.isDone = true;
    }

    /**
     * Converts task to an appropriate String representation with the status
     * @return formatted string
     */
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

}
