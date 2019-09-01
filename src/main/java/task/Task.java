package duke.task;

/**
 * Represents an task.
 * Has functions to print the task information to a standard form,
 * to set and get the given time of the deadline.
 */
public class Task {
    protected char taskType;
    protected String taskDescription;
    protected boolean isDone;

    public Task() {
        this.taskDescription = "";
        this.isDone = false;
    }

    public Task(String taskDescription, boolean isDone) throws NullPointerException {
        this.isDone = isDone;

        if (taskDescription == null) {
            throw new NullPointerException();
        }
        this.taskDescription = taskDescription;
    }

    public Task(char taskType, String taskDescription, boolean isDone) throws NullPointerException {
        this.isDone = isDone;
        this.taskType = taskType;

        if (taskDescription == null) {
            throw new NullPointerException();
        }
        this.taskDescription = taskDescription;
    }

    /**
     * Returns string representation of task,
     * in terms of initial, icon of done, task description.
     *
     * @return String representation of task.
     */
    public String printTask() {
        return "[" + getFirstCharTask() + "][" + getIcon() + "] " + getTaskDescription();
    }

    /**
     * Returns char icon.
     * Returns tick if isDone is true, else returns cross.
     *
     * @return char icon.
     */
    public char getIcon() {
        return isDone ? '\u2713' : '\u274C';
    }

    /**
     * Indicate that task is done.
     * Set isDone true when function is called.
     */
    public void setIsDone() {
        this.isDone = true;
    }

    /**
     * Returns char of first letter in task name.
     *
     * @return char of first letter in task name.
     */
    public char getFirstCharTask() {
        return taskType;
    }

    /**
     * Get isDone,
     * indicating done status of the task.
     *
     * @return boolean true if done, else false.
     */
    public boolean getIsDone() {
        return isDone;
    }

    /**
     * Get task description for task.
     *
     * @return String task description.
     */
    public String getTaskDescription() {
        return taskDescription;
    }

    /**
     * Set task description of task.
     *
     * @param input task description from user.
     */
    public void setTaskDescription(String input) {
        this.taskDescription = input;
    }

}

