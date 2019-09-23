package duke.task;

/**
 * The class that is used to represent various tasks.
 */
public class Task {

    /**
     * The type of task.
     */
    protected String type = "";

    /**
     * The main message of the task.
     */
    protected String description;

    /**
     * Indicates the completion status of the task.
     */
    protected boolean isDone;

    /**
     * Extra information of the task.
     */
    protected String extraInfo;

    /**
     * Constructor that takes in the main message of the task.
     * @param description The main description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.extraInfo = "";
    }

    /**
     * Used to return the type of task.
     * @return The type of task in String form.
     */
    public String getType() {
        return this.type;
    }

    /**
     * Used to obtain the task completion status.
     * @return True if the task is completed, false otherwise.
     */
    public boolean getStatus() {
        return isDone;
    }

    /**
     * Used to obtain the main message of the task.
     * @return The description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Used to obtain any extra information of the task.
     * @return The extra information of the task.
     */
    public String getExtraInfo() {
        return this.extraInfo;
    }

    /**
     * Used to return the status icon based on completion status.
     * @return Icon which relies on the completion status.
     */
    public String getStatusIcon() {
        return (isDone ? "V" : "X");
    }

    /**
     * Used to mark a task as complete.
     */
    public void completeTask() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return "[ " + this.getStatusIcon() + " ] " + this.description;
    }
}
