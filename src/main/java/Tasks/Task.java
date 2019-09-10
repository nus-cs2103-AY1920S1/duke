package Tasks;

/**
 * An object of a Task task.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Creates an instance of a Task task
     *
     * @param description is the description of the task
     */
    public Task(String description) {
        assert(description.length()!=0):"Task description cannot be empty";
        this.description = description;
        this.isDone = false;
    }

    /**
     * Checks if the task if done. returns a string representing the state of
     * object "done-ness".
     *
     * @return "O" if task is done and "X" if task is not done
     */
    public String getStatusIcon() {
        return (isDone ? "O" : "X"); //O represents tick and X represents cross as it doesn't work for me
    }

    /**
     * Marks object as done regardless of whether it was already done or not.
     */
    public void doneJob(){
        isDone = true;
    }

    /**
     * Getter for Task object description
     *
     * @return description of task
     */
    public String getDescription() {
        return description;
    }

    /**
     * Setter for Task object descriptions
     *
     * @param description refers to description of task object
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Overrides toString method
     *
     * @return String of task object. (i.e. "[O] do task")
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + description;
    }
}